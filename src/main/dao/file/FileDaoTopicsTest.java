package main.dao.file;

import main.dao.IDaoTopics;
import main.system.entity.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileDaoTopicsTest extends Assert {
	File f;
	IDaoTopics fileDaoTopics;

	@Before
	public void setUp() throws Exception {
		try {
			f= File.createTempFile("tmp", ".txt");
			FileOutputStream fileOutputStream=new FileOutputStream(f);
			String str="1;Topic1;this is test Topic1;\n" +
					"2;Topic2;this is test Topic2;\n" +
					"3;Topic3;this is test Topic3;\n";
			fileOutputStream.write(str.getBytes());
			fileOutputStream.close();
			fileDaoTopics = new FileDaoTopics(f,false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetAllTopics() throws Exception {
		ArrayList<Topic> arrayList=fileDaoTopics.getAllTopics();
		assertEquals(arrayList.size(),3);
		assertTrue(arrayList.get(0).equals(new Topic(1,"Topic1","this is test Topic1")));
		assertTrue(arrayList.get(1).equals(new Topic(2,"Topic2","this is test Topic2")));
		assertTrue(arrayList.get(2).equals(new Topic(3,"Topic3","this is test Topic3")));
	}

	@Test
	public void testAdd() throws Exception{
		assertEquals(fileDaoTopics.getRowCount(),3);
		int k=1000;
		for(int i=0;i<k;i++){
			assertTrue(fileDaoTopics.add(new Topic((4+i),"Topic"+(4+i),"this is test Topic"+(4+i))));
		}
		assertEquals(fileDaoTopics.getRowCount(),k+3);
		assertTrue(fileDaoTopics.add(new Topic((k+4),"Topic"+(k+4),"this is test Topic"+(k+4))));
		assertEquals(fileDaoTopics.getTopic(k),new Topic(k,"Topic"+k,"this is test Topic"+k));
		assertEquals(fileDaoTopics.getRowCount(),fileDaoTopics.getAllTopics().size());
	}

	@Test
	public void testRemove() throws Exception{
		Topic t=new Topic(4,"Topic"+4,"this is test Topic"+4);
		assertEquals(fileDaoTopics.getRowCount(),3);
		assertTrue(fileDaoTopics.add(t));
		assertEquals(fileDaoTopics.getRowCount(),4);
		assertEquals(fileDaoTopics.getTopic(t.getId()),t);
		assertTrue(fileDaoTopics.remove(t));
		assertEquals(fileDaoTopics.getRowCount(),3);
		assertNull(fileDaoTopics.getTopic(t.getId()));
		assertTrue(fileDaoTopics.remove(1));
		assertEquals(fileDaoTopics.getRowCount(),2);
		assertNull(fileDaoTopics.getTopic(1));
	}

	@Test
	public void testGetTopic() throws Exception{
		Topic t=new Topic(3,"Topic"+3,"this is test Topic"+3);
		assertEquals(fileDaoTopics.getRowCount(),3);
		assertEquals(fileDaoTopics.getTopic(t.getId()),t);
	}
}