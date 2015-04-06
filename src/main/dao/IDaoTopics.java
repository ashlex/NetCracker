package main.dao;

import main.system.entity.Topic;

import java.util.ArrayList;

public interface IDaoTopics {
	/**
	 * This add new topic
	 * @param topic It's the topic to adding.
	 * @return If success {@code true}, else {@code false}.
	 */
	public boolean add(Topic topic);
	public boolean remove(Topic topic);
	public boolean remove(int id);

	/**
	 * @param id {@code int}This is topic's id for removal.
	 * @return if found then returns {@code Topic}, or null if not found.
	 */
	public Topic getTopic(int id);
	public ArrayList<Topic> getAllTopics();

	/**
	 * @return count of topics
	 */
	public int getRowCount();
}
