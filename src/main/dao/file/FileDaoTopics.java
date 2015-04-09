package main.dao.file;

import main.dao.IDaoTopics;
import main.dao.file.entity.Row;
import main.system.entity.Topic;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDaoTopics implements IDaoTopics {
	Parser parser;
	private static final Logger log = Logger.getLogger(FileDaoCommandHelp.class.getName());
	private int rowCount = -1;
	private int operationAfterSynchronisation = 0;
	private final int MAX_OPERATION_BEFORE_SYNCHRONISATION = 1000;

	public FileDaoTopics(File topicsDF, boolean header) {
		if (topicsDF == null) {
			throw new IllegalArgumentException("Object topicsDF is null.");
		}
		parser = new Parser(topicsDF, header);
		rowCount = getRowCount();
	}

	public FileDaoTopics(File topicsDF) {
		parser = new Parser(topicsDF, true);
	}

	@Override
	public boolean add(Topic topic) {
		if (topic != null) {
			String[] topicString = {String.valueOf(topic.getId()), topic.getHeader(),
					topic.getDescription(), String.valueOf(topic.getUserId())};
			Row<String> row = new Row<String>(topicString);
			ArrayList<Row> arrayList = parser.getRows();
			if (arrayList.add(row)) {
				if (parser.write(arrayList)) {
					rowCount++;
					synchronisationRowCount();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean remove(Topic topic) {
		if (topic == null) {
			return false;
		}
		return remove(topic.getId());
	}

	@Override
	public boolean remove(int id) {
		if (id > 0) {
			ArrayList<Row> arrayList = parser.getRows();
			for (Row<String> row : arrayList) {
				if (Integer.valueOf(row.getRow()[0]) == id) {
					if (arrayList.remove(row)) {
						if (parser.write(arrayList)) {
							rowCount--;
							synchronisationRowCount();
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public Topic getTopic(int id) {
		ArrayList<Row> arrayList = parser.getRows();
		for (Row<String> row : arrayList) {
			if (Integer.valueOf(row.getRow()[0]) == id) {
				try {
					return new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2], Integer.valueOf(row.getRow()[3]));
				} catch (ArrayIndexOutOfBoundsException e) {
					log.log(Level.SEVERE, row.toString(), e);
				}
			}
		}
		return null;
	}

	@Override
	public Topic getTopic(String header) {
		ArrayList<Row> arrayList = parser.getRows();
		for (Row<String> row : arrayList) {
			if (header.equalsIgnoreCase(row.getRow()[1])) {
				try {
					return new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2], Integer.valueOf(row.getRow()[3]));
				} catch (ArrayIndexOutOfBoundsException e) {
					log.log(Level.SEVERE, row.toString(), e);
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		for (Row<String> row : parser.getRows()) {
			try {
				topics.add(new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2], Integer.valueOf(row.getRow()[3])));
			} catch (ArrayIndexOutOfBoundsException e) {
				log.log(Level.SEVERE, row.toString(), e);
			}
		}
		rowCount = topics.size();
		operationAfterSynchronisation = 0;
		return topics;
	}
	@Override
	public ArrayList<Topic> getAllTopics(int userId) {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		for (Row<String> row : parser.getRows()) {
			if(Integer.valueOf(row.getRow()[3])==userId||Integer.valueOf(row.getRow()[3])==0) {
				try {
					topics.add(new Topic(Integer.valueOf(row.getRow()[0]), row.getRow()[1], row.getRow()[2], Integer.valueOf(row.getRow()[3])));
				} catch (ArrayIndexOutOfBoundsException e) {
					log.log(Level.SEVERE, row.toString(), e);
				}
			}
		}
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
