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
			fileDaoTopics = new FileDaoTopics(f);
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
}