package main.dao;

import main.entity.UserContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileDaoUserContextTest {
	File f;
	FileDaoUserContext fileDaoUserContext;

	@Before
	public void beforeTest(){
		try {
			f=File.createTempFile("tmp",".txt");
			fileDaoUserContext=new FileDaoUserContext(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAdd() throws Exception {
		UserContext context=new UserContext();
		context.setNickname("test0");
		UserContext context1=new UserContext();
		context1.setNickname("test1");
		UserContext context2=new UserContext();
		context2.setNickname("test0");
		try{
			fileDaoUserContext.add(null);
			Assert.fail();
		}catch (IllegalArgumentException ie){}
		Assert.assertTrue(fileDaoUserContext.add(context));
		Assert.assertTrue(fileDaoUserContext.add(context1));
		Assert.assertFalse(fileDaoUserContext.add(context2));
		Assert.assertEquals(fileDaoUserContext.getCountUsers(), 2);
		Assert.assertTrue(fileDaoUserContext.remove(context.getNickname()));
		Assert.assertTrue(fileDaoUserContext.remove(context1.getNickname()));
		Assert.assertEquals(fileDaoUserContext.getCountUsers(), 0);

	}

	@Test
	public void testGetUser() throws Exception {
	}

	@Test
	public void testRemove() throws Exception {

	}

	@Test
	public void testUpdate() throws Exception {

	}

	@Test
	public void testGetCountUsers() throws Exception {

	}
}