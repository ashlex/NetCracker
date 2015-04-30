package main.dao.hibernate;

import main.dao.IDaoTopics;
import main.system.entity.Topic;

import java.util.ArrayList;

public class DaoTopics implements IDaoTopics {
	@Override
	public boolean add(Topic topic) {
		return false;
	}

	@Override
	public boolean remove(Topic topic) {
		return false;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}

	@Override
	public Topic getTopic(int id) {
		return null;
	}

	@Override
	public Topic getTopic(String header) {
		return null;
	}

	@Override
	public ArrayList<Topic> getAllTopics() {
		return null;
	}

	@Override
	public ArrayList<Topic> getAllTopics(int userId) {
		return null;
	}

	@Override
	public int getRowCount() {
		return 0;
	}
}
