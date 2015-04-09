package main.system.command;

import main.command.AbstractCommandBase;
import main.command.CommandBuilder;
import main.command.IInvoker;
import main.command.entity.Attributes;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.dao.IDaoTopics;
import main.system.entity.Topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Topics extends AbstractCommandBase {
	private IInvoker invoker;
	private CommandBuilder commandBuilder;

	public Topics(String alias) {
		super(alias);
		invoker = null;
		commandBuilder = null;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		Map<String, Attributes.Attribute> attributeMap = attributes.getAllAttribute();
		IDaoTopics iDaoTopics = daoFactory.getDaoTopics();
		if (isHelp()) {
			return new ExecuteResult(this, ExecuteResult.GET_HELP, new Response(getHelp()));
		}
		if (attributeMap != null) {

			if (attributeMap.containsKey("all")) {
				return keyAll();
			} else if (attributeMap.containsKey("t")) {
				Attributes.Attribute t = attributeMap.get("t");
				if (t.getValues().length > 0) {
					StringBuilder stringBuilder = new StringBuilder();
					String[] strings = t.getValues();
					for (int i = 0; i < strings.length; i++) {
						stringBuilder.append(t.getValues()[i]);
					}
					String header = stringBuilder.toString();
					boolean isNumber = false;
					int id = 0;
					try {
						id = Integer.parseInt(header);
						isNumber = true;
					} catch (NumberFormatException e) {
						isNumber = false;
					}

					if (attributeMap.containsKey("new")) {
						String description = "";
						if (attributeMap.containsKey("d")) {
							Attributes.Attribute d = attributeMap.get("d");
							stringBuilder = new StringBuilder();
							strings = d.getValues();
							for (int i = 0; i < strings.length; i++) {
								stringBuilder.append(d.getValues()[i]);
							}
							description = stringBuilder.toString();
						}
						int identifier = iDaoTopics.getRowCount() + 1;
						Topic topic=new Topic(identifier, header, description, context.getId());
						if (iDaoTopics.add(topic)) {
							Response response = new Response(String.format(resourceBundle.getString("ADDED_NEW_TOPIC"), header));
							return new ExecuteResult(this, ExecuteResult.SUCCESS, response);
						} else {
							Response response = new Response(resourceBundle.getString("ADDED_NEW_TOPIC_FAILED"));
							return new ExecuteResult(this, ExecuteResult.FAIL, response);
						}
					} else if (attributeMap.containsKey("o") || attributeMap.containsKey("open")) {
						Topic topic;
						if (isNumber) {
							topic = iDaoTopics.getTopic(id);
						} else {
							topic = iDaoTopics.getTopic(header);
						}
						if (topic != null) {
							context.setCurrentTopic(topic);
							context.notifyObserver();
							return new ExecuteResult(this, ExecuteResult.SUCCESS, null);
						} else {
							return new ExecuteResult(this, ExecuteResult.SUCCESS, new Response(String.format(resourceBundle.getString("TOPIC_NOT_FOUND"), header)));
						}
					} else if (attributeMap.containsKey("rm") || attributeMap.containsKey("remove")) {
						if (iDaoTopics.remove(isNumber ? iDaoTopics.getTopic(id) : iDaoTopics.getTopic(header))) {
							return new ExecuteResult(this, ExecuteResult.SUCCESS, new Response(String.format(resourceBundle.getString("TOPIC_REMOVED"), header)));
						} else {
							String responseString = resourceBundle.getString("TOPIC_NOT_REMOVED") +
									String.format(resourceBundle.getString("TOPIC_NOT_FOUND"), header);
							return new ExecuteResult(this, ExecuteResult.FAIL, new Response(responseString));
						}
					}
				}else{
					// сообщение о том что должен быть указан заголовок или id темы
				}
			}else if(attributeMap.containsKey("q")||attributeMap.containsKey("quit")){
				if(context.getCurrentTopic()!=null){
					context.setCurrentTopic(null);
					context.notifyObserver();
					return new ExecuteResult(this, ExecuteResult.SUCCESS, null);
				}else{
					return new ExecuteResult(this, ExecuteResult.FAIL, new Response("You have no open topics."));
				}
			}
		} else {
			return keyAll();
		}
		return executeResult;
	}

	private ExecuteResult keyAll() {
		ArrayList<Topic> topics = daoFactory.getDaoTopics().getAllTopics(context.getId());
		ArrayList<String> headers = new ArrayList<String>(topics.size());
		for (Topic t : topics) {
			headers.add(t.getId() + " " + t.getHeader());
		}
		return new ExecuteResult(this, ExecuteResult.SUCCESS, new Response(headers));
	}
}
