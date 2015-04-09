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

	/**
	 * @param id {@code int}This is topic's id for removal.
	 * @return true if removed and false if not.
	 */
	public boolean remove(int id);

	/**
	 * @param id {@code int}This is topic's id for search.
	 * @return if found then returns {@code Topic}, or null if not found.
	 */
	public Topic getTopic(int id);

	/**
	 * @param header {@code String}This is topic's header for search.
	 * @return if found then returns {@code Topic}, or null if not found.
	 */
	public Topic getTopic(String header);

	public ArrayList<Topic> getAllTopics();
	public ArrayList<Topic> getAllTopics(int userId);

	/**
	 * @return count of topics
	 */
	public int getRowCount();
}
