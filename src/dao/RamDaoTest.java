package dao;

import org.junit.Assert;
import org.junit.Test;

public class RamDaoTest {
	IDaoUser iDaoUser=DaoFactory.getInstance().getDaoUser("RamDaoUser");

	@Test
	public void testAdd() throws Exception {
		Assert.assertNotNull(iDaoUser.add("User1","pass1"));
		Assert.assertNotNull(iDaoUser.add("User2","pass2"));
		Assert.assertNotNull(iDaoUser.add("User3","pass3"));
		Assert.assertNotNull(iDaoUser.add("User4", "pass4"));
		Assert.assertNull(iDaoUser.add("User4", "pass4"));
		Assert.assertEquals(iDaoUser.getAllUser().size(), 4);
		Assert.assertTrue(iDaoUser.remove("User1"));
		Assert.assertTrue(iDaoUser.remove("User2"));
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertTrue(iDaoUser.remove("User4"));
		Assert.assertEquals(iDaoUser.getAllUser().size(), 0);
	}

	@Test
	public void testAdd1() throws Exception {
		Assert.assertNotNull(iDaoUser.add("User1"));
		Assert.assertNotNull(iDaoUser.add("User2"));
		Assert.assertNotNull(iDaoUser.add("User3"));
		Assert.assertNotNull(iDaoUser.add("User4"));
		Assert.assertNull(iDaoUser.add("User4"));
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
		Assert.assertNotNull(iDaoUser.add("User1"));
		Assert.assertNotNull(iDaoUser.add("User2"));
		Assert.assertNotNull(iDaoUser.add("User3"));
		Assert.assertNotNull(iDaoUser.add("User4"));
		Assert.assertEquals(iDaoUser.getAllUser().size(), 4);
		Assert.assertTrue(iDaoUser.remove("User3"));
		Assert.assertEquals(iDaoUser.getAllUser().size(), 3);

	}

	@Test
	public void testGetAllUser() throws Exception {
		Assert.assertNotNull(iDaoUser.add("User1"));
		Assert.assertNotNull(iDaoUser.add("User2"));
		Assert.assertNotNull(iDaoUser.add("User3"));
		Assert.assertNotNull(iDaoUser.add("User4"));
		Assert.assertEquals(iDaoUser.getAllUser().size(),4);
	}
}