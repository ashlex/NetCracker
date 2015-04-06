package main.system.command;

import main.command.AbstractCommandBase;
import main.command.entity.Attributes;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.dao.IDaoTopics;
import main.system.entity.Topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ShowAllTopics extends AbstractCommandBase{

	public ShowAllTopics(String alias){
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		Map<String,Attributes.Attribute> attributeMap=attributes.getAllAttribute();
		IDaoTopics iDaoTopics=daoFactory.getDaoTopics();
		if (isHelp()) {
			return new ExecuteResult(this,ExecuteResult.GET_HELP,new Response(getHelp()));
		}
		if(attributeMap.containsKey("all")){
			ArrayList<Topic> topics=iDaoTopics.getAllTopics();
			ArrayList<String> headers=new ArrayList<String>(topics.size());
			for (Topic t:topics){
				headers.add(t.getId()+" "+t.getHeader());
			}
			return new ExecuteResult(this,ExecuteResult.SUCCESS,new Response(headers));
		}else if(attributeMap.containsKey("t")){
			Attributes.Attribute t=attributeMap.get("t");
			if(t.getValues().length>0) {
				if (attributeMap.containsKey("new")) {
					StringBuilder stringBuilder=new StringBuilder();
					String[] strings=t.getValues();
					for(int i=0;i<strings.length;i++) {
						stringBuilder.append(t.getValues()[i]);
					}
					String header=stringBuilder.toString();
					String description="";

					if(attributeMap.containsKey("d")){
						Attributes.Attribute d=attributeMap.get("d");
						stringBuilder=new StringBuilder();
						strings=d.getValues();
						for(int i=0;i<strings.length;i++) {
							stringBuilder.append(d.getValues()[i]);
						}
						description=stringBuilder.toString();
					}
					int id = iDaoTopics.getRowCount()+1;
					if(iDaoTopics.add(new Topic(id,header,description))) {
						Response response=new Response(String.format(resourceBundle.getString("ADDED_NEW_TOPIC"), header));
						return new ExecuteResult(this, ExecuteResult.SUCCESS, response);
					}else {
						Response response=new Response(resourceBundle.getString("ADDED_NEW_TOPIC_FAILED"));
						return new ExecuteResult(this, ExecuteResult.FAIL, response);
					}
				} else {
					return new ExecuteResult(this, ExecuteResult.SUCCESS,new Response("There will added info of concrete topic."));
				}
			}
		}
		return executeResult;
	}

}
