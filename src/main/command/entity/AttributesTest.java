package main.command.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class AttributesTest extends Assert {

	@Test
	public void testGetAllAttribute() throws Exception {
		String line="";
		Map<String, Attributes.Attribute> map;
		Attributes a=new Attributes(line);
		assertNull(a.getAllAttribute());
		line=" com - dfs";
		a=new Attributes(line);
		map=a.getAllAttribute();
		assertNull(map);
		line=" com -dfs er -sr";
		a=new Attributes(line);
		map=a.getAllAttribute();
		assertEquals(map.size(),2);
		assertNotNull(map.get("dfs"));
		assertNotNull(map.get("dfs").getValues());
		assertEquals(map.get("dfs").getValues().length,1);
		assertEquals(map.get("dfs").getValues()[0],"er");
	}
}