package main.command.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Attributes {
	private Map<String, Attribute> attributes;

	public Attributes(String line) {
		attributes=parseLine(line);
	}


	/**
	 *
	 * @return {@link java.util.Map} whit attributes' names and {@link main.command.entity.Attributes.Attribute} or null
	 */
	public Map<String,Attribute> getAllAttribute(){
		return attributes;
	}
	/**
	 * Parses the entered string from the console.
	 *
	 * @param line Text entered from the console.
	 * @return {@link java.util.Map} whit attributes' names and {@link main.command.entity.Attributes.Attribute} or null
	 */
	private Map<String, Attribute> parseLine(String line) {
		HashMap<String, Attribute> attributeMap=null;
		String tmp[] = line.split("\\s+");
		int i = 0;
		if (!tmp[i].startsWith("-")) { // If first element is command's name.
			i++;
		}
		if (tmp.length > 1) {

			Attribute a=null;
			for (; i < tmp.length; i++) {
				if (tmp[i].startsWith("-")) {
					String s=tmp[i].substring(1);
					if("".equals(s)){
						a=null;
					}else {
						if(attributeMap==null){
							attributeMap = new HashMap<String, Attribute>();
						}
						attributeMap.put(s, a = new Attribute(s));
					}
				}else if(a!=null){
					a.addValue(tmp[i]);
				}
			}
		} else {
			attributeMap = null;
		}
		return attributeMap;
	}



	public class Attribute {
		private String key;
		private String[] values;
		private int length;
		private int lastElementIndex;

		Attribute(String key, String... values) {
			this.key = key;
			this.values = values;
			length = values.length;
			lastElementIndex = values.length;
		}

		/**
		 * Add new value
		 *
		 * @param value
		 * @return count of values inside this attribute
		 */
		private int addValue(String value) {
			if (lastElementIndex < length) {
				values[lastElementIndex++] = value;
				return lastElementIndex;
			}
			length = length + 2;
			values = Arrays.copyOf(values, length);
			return addValue(value);
		}

		public String getKey() {
			return this.key;
		}

		public String[] getValues() {
			return Arrays.copyOf(values,lastElementIndex);
		}
	}
}
