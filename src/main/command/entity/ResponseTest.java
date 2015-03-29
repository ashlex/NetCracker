package main.command.entity;

import main.dao.entity.Row;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResponseTest extends Assert {
	Response response;

	@After
	public void setDown() throws Exception {
		response = null;
	}

	@Test
	public void testGetType() throws Exception {
		response = new Response("test");
		assertEquals(response.getType(), Response.STRING);
		printType(response.getType());

		ArrayList<String> strings = new ArrayList<String>();
		strings.add("test1");
		strings.add("test2");
		strings.add("test3");
		response = new Response(strings);
		assertEquals(response.getType(), Response.LIST);
		printType(response.getType());

		response = new Response(strings, true);
		assertEquals(response.getType(), Response.NUMBERED_LIST);
		printType(response.getType());

		response = new Response(strings, false);
		assertEquals(response.getType(), Response.LIST);
		printType(response.getType());

		Object[] o = {1, 2, 3, 4, 5, 6};
		response = new Response(o);
		assertEquals(response.getType(), Response.LIST);
		printType(response.getType());

		Object[][] o1 = {{1, 2}, {3, 4}, {5, 6}};
		response = new Response(o1);
		assertEquals(response.getType(), Response.TABLE);
		printType(response.getType());

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "2");
		map.put("3", "4");
		map.put("5", "6");
		response = new Response(map);
		assertEquals(response.getType(), Response.TABLE);
		printType(response.getType());
	}

	@Test
	public void testGetResponseArray() throws Exception {
		String s = "test";
		response = new Response(s);
		assertEquals((String) response.getResponseArray()[0], s);
		assertEquals(response.getResponseArray().length, 1);

		ArrayList<String> strings = new ArrayList<String>();
		strings.add("test1");
		strings.add("test2");
		strings.add("test3");
		response = new Response(strings);
		assertEquals(response.getResponseArray().length, 3);
		assertEquals((String) response.getResponseArray()[0], "test1");
		assertEquals((String) response.getResponseArray()[1], "test2");
		assertEquals((String) response.getResponseArray()[2], "test3");

		Object[] o = {1, 2, 3, 4, 5, 6};
		response = new Response(o);
		assertEquals(response.getResponseArray().length, 6);
		assertEquals((Integer) response.getResponseArray()[0], Integer.valueOf(1));
		assertEquals((Integer) response.getResponseArray()[1], Integer.valueOf(2));
		assertEquals((Integer) response.getResponseArray()[2], Integer.valueOf(3));
		assertEquals((Integer) response.getResponseArray()[3], Integer.valueOf(4));
		assertEquals((Integer) response.getResponseArray()[4], Integer.valueOf(5));
		assertEquals((Integer) response.getResponseArray()[5], Integer.valueOf(6));

		Object[][] o1 = {{1, 2, 12}, {3, 4, 34}, {5, 6, 56}};
		response = new Response(o1);
		int i = 0;
		for (Object objects : response.getResponseArray()) {
			assertTrue(objects instanceof Row);
			assertEquals(((Row) objects).getRow().length, o1[0].length);
			for (int j = 0; j < ((Row) objects).getRow().length; j++) {
//				System.out.println("i"+i+"=" + ((Integer) ((Row) objects).getRow()[j]).intValue() + ":" + ((Integer) o1[i][j]).intValue());
				assertTrue(((Integer) ((Row) objects).getRow()[j]).intValue() == ((Integer) o1[i][j]).intValue());
			}
			i++;
		}


		Map<String, String> map = new TreeMap<String, String>();
		map.put("1", "2");
		map.put("3", "4");
		map.put("5", "6");
		response = new Response(map);
		for (Object objects : response.getResponseArray()) {
			assertTrue(objects instanceof Row);
			assertEquals(((Row) objects).getRow().length, 2);
				System.out.println(((String) ((Row) objects).getRow()[0]) + ":" +((String) ((Row) objects).getRow()[1]));
			assertTrue(((String) ((Row) objects).getRow()[1]).equals(map.get(((String) ((Row) objects).getRow()[0]))));
		}
	}

	private void printType(int type) {
		String s;
		switch (type) {
			case Response.STRING:
				s = "STRING";
				break;
			case Response.LIST:
				s = "LIST";
				break;
			case Response.NUMBERED_LIST:
				s = "NUMBERED_LIST";
				break;
			case Response.TABLE:
				s = "TABLE";
				break;
			default:
				s = "UNDEFENDED";
		}
//		System.out.println(s);
	}
}