package main.dao.file;

import main.dao.IDaoTopics;
import main.dao.entity.Row;
import main.system.IIterator;
import main.system.entity.Topic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FileDaoTopics implements IDaoTopics {
	private File topicsDF;
	private static final Logger log = Logger.getLogger(FileDaoCommandHelp.class.getName());

	public FileDaoTopics(File topicsDF){
		if (topicsDF == null) {
			throw new IllegalArgumentException("Object commandHelpDF is null.");
		}
		this.topicsDF=topicsDF;
	}

	@Override
	public boolean add(Topic topic) {
		return false;
	}

	@Override
	public Topic getTopic(int id) {
		return null;
	}

	@Override
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> topics=new ArrayList<Topic>();
		try {
			Parser parser=new Parser(topicsDF,true);
			IIterator it=parser.getIterator();
			while (it.hasNext()){
				Row<String> r=(Row<String>)it.next();
				topics.add(new Topic(Integer.valueOf(r.getRow()[0]),r.getRow()[1],r.getRow()[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return topics;
	}
}
