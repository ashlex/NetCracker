package main.system.entity;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest extends Assert{

	@Test
	public void testEquals() throws Exception {
		Topic t1=new Topic(1,"te","tesfa");
		Topic t2=new Topic(1,"te","tesfa");
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
	}
}