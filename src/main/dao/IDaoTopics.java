package main.dao;

import main.system.entity.Topic;

import java.util.ArrayList;

public interface IDaoTopics {
	public boolean add(Topic topic);
	public Topic getTopic(int id);
	public ArrayList<Topic> getAllTopics();
}
