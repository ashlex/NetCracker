package dao;

import entity.User;
import org.junit.Assert;
import org.junit.Test;

public class FileDaoUserTest {
	IDaoUser iDaoUser=DaoFactory.getInstance().getFileDaoUser();

	@Test
	public void testAdd() {
		User u1=new User("User1","pas");
		User u2=new User("User2","pas");
		User u3=new User("User3","pas");
		User u4=new User("User4","pas");
		Assert.assertNotNull(u1);
		Assert.assertNotNull(u2);
		Assert.assertNotNull(u3);
		Assert.assertNotNull(u4);
		Assert.assertTrue(iDaoUser.remove("User1"));
		Assert.assertTrue(iDaoUser.remove("User2"));
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertTrue(iDaoUser.remove("User4"));

	}

	@Test
	public void testAdd1() throws Exception {
		User u1=new User("User1","pas");
		User u2=new User("User2","pas");
		User u3=new User("User3","pas");
		User u4=new User("User4","pas");
		Assert.assertNotNull(u1);
		Assert.assertNotNull(u2);
		Assert.assertNotNull(u3);
		Assert.assertNotNull(u4);
		Assert.assertEquals(iDaoUser.getAllUser().size(),4);
		Assert.assertTrue(iDaoUser.remove("User1"));
		Assert.assertTrue(iDaoUser.remove("User2"));
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertTrue(iDaoUser.remove("User4"));
		Assert.assertEquals(iDaoUser.getAllUser().size(),0);
	}

	@Test
	public void testGetUser() throws Exception {


	}

	@Test
	public void testRemove() throws Exception {
		User u1=new User("User1","pas");
		User u2=new User("User2","pas");
		User u3=new User("User3","pas");
		User u4=new User("User4","pas");
		Assert.assertNotNull(u1);
		Assert.assertNotNull(u2);
		Assert.assertNotNull(u3);
		Assert.assertNotNull(u4);
		Assert.assertEquals(iDaoUser.getAllUser().size(), 4);
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertEquals(iDaoUser.getAllUser().size(), 3);
		Assert.assertTrue(iDaoUser.remove("User1"));
		Assert.assertTrue(iDaoUser.remove("User2"));
		Assert.assertTrue(iDaoUser.remove("User4"));
	}

	@Test
	public void testGetAllUser() throws Exception {
		User u1=new User("User1","pas");
		User u2=new User("User2","pas");
		User u3=new User("User3","pas");
		User u4=new User("User4","pas");
		Assert.assertNotNull(u1);
		Assert.assertNotNull(u2);
		Assert.assertNotNull(u3);
		Assert.assertNotNull(u4);
		Assert.assertEquals(iDaoUser.getAllUser().size(), 4);
		Assert.assertTrue(iDaoUser.remove("User1"));
		Assert.assertTrue(iDaoUser.remove("User2"));
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertTrue(iDaoUser.remove("User4"));
	}
}