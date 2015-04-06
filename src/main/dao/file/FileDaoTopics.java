package main.dao.file;

import main.dao.IDaoTopics;
import main.dao.entity.Row;
import main.system.entity.Topic;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FileDaoTopics implements IDaoTopics {
	Parser parser;
	private static final Logger log = Logger.getLogger(FileDaoCommandHelp.class.getName());
	private int rowCount = -1;
	private int operationAfterSynchronisation = 0;
	private final int MAX_OPERATION_BEFORE_SYNCHRONISATION = 1000;

	public FileDaoTopics(File topicsDF, boolean header) {
		if (topicsDF == null) {
			throw new IllegalArgumentException("Object commandHelpDF is null.");
		}
		parser = new Parser(topicsDF, header);
	}

	public FileDaoTopics(File topicsDF) {
		if (topicsDF == null) {
			throw new IllegalArgumentException("Object commandHelpDF is null.");
		}
		parser = new Parser(topicsDF, true);
	}

	@Override
	public boolean add(Topic topic) {
		String[] topicString = {String.valueOf(topic.getId()), topic.getHeader(), topic.getDescription()};
		Row<String> row = new Row<String>(topicString);
		ArrayList<Row> arrayList = parser.getRows();
		if(arrayList.add(row)) {
			if(parser.write(arrayList)) {
				rowCount++;
				synchronisationRowCount();
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean remove(Topic topic) {
		return remove(topic.getId());
	}

	@Override
	public boolean remove(int id) {
		ArrayList<Row> arrayList=parser.getRows();
		for (Row<String> row : arrayList) {
			if(Integer.valueOf(row.getRow()[0])==id){
				if(arrayList.remove(row)){
					if(parser.write(arrayList)) {
						rowCount--;
						synchronisationRowCount();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Topic getTopic(int id) {
		ArrayList<Row> arrayList=parser.getRows();
		for (Row<String> row : arrayList) {
			if(Integer.valueOf(row.getRow()[0])==id){
				return new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2]);
			}
		}
		return null;
	}

	@Override
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		for (Row<String> row : parser.getRows()) {
			topics.add(new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2]));
		}
		rowCount = topics.size();
		operationAfterSynchronisation = 0;
		synchronisationRowCount();

		return topics;
	}

	public int getRowCount() {
		if (rowCount >= 0) {
			return rowCount;
		} else {
			rowCount = parser.getRows().size();
			operationAfterSynchronisation = 0;
			return rowCount;
		}
	}

	private boolean synchronisationRowCount() {
		if (operationAfterSynchronisation < MAX_OPERATION_BEFORE_SYNCHRONISATION) {
			return false;
		} else {
			rowCount = parser.getRows().size();
			operationAfterSynchronisation = 0;
			return true;
		}
	}
}
