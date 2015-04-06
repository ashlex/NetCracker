package main.command.entity;


import main.dao.file.entity.Row;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This class describes the response when the command. It need for view. It describes type of response.
 */
public class Response {
	public final static int LIST = 1;
	public final static int NUMBERED_LIST = 2;
	public final static int STRING = 3;
	public final static int TABLE = 4;
	public final static int IMAGE = 5;
	private final int typeCount = 5;


	private final int type;
	private ArrayList<Object> ar;

	/**
	 * Setting {@code type} as {@code STRING}
	 *
	 * @param response String of response of command
	 */

	public Response(String response) {
		type = STRING;
		ar= new ArrayList<Object>();
		ar.add(response);
	}

	/**
	 * Setting {@code type} as {@code LIST}
	 *
	 * @param response Response's collection of command
	 */
	public Response(Collection<String> response) {
		this(response, false);
	}

	/**
	 * Setting {@code type} if {@code numbered=false} as {@code LIST} or if {@code numbered=true} as {@code NUMBERED_LIST}
	 *
	 * @param response Response's collection of command
	 * @param numbered If type is {@code NUMBERED_LIST}, then {@code numbered} must be {@code true}, else {@code false}
	 */
	public Response(Collection<String> response, boolean numbered) {
		if (numbered) {
			this.type = NUMBERED_LIST;
		} else {
			this.type = LIST;
		}
		ar=(ArrayList)response;
	}

	/**
	 * Setting {@code type} as {@code TABLE}
	 *
	 * @param response Response's map of command
	 */
	public Response(Map response) {
		type = TABLE;
		ar=new ArrayList<Object>(response.size());
		Object [] ob;
		for (Object e:response.entrySet()){
			ob=new Object[]{((Map.Entry<Object, Object>) e).getKey(), ((Map.Entry<Object, Object>) e).getValue()};
			ar.add(new Row<Object>(ob));
		}
	}

	/**
	 * Setting {@code type} as {@code LIST}
	 *
	 * @param response Response's array of command
	 */
	public Response(Object[] response) {
		this(response,false);
	}

	/**
	 * Setting {@code type} if {@code numbered=false} as {@code LIST} or if {@code numbered=true} as {@code NUMBERED_LIST}
	 *
	 * @param response Response's array of command
	 * @param numbered If type is {@code NUMBERED_LIST}, then {@code numbered} must be {@code true}, else {@code false}
	 */
	public Response(Object[] response,boolean numbered) {
		if (numbered) {
			this.type = NUMBERED_LIST;
		} else {
			this.type = LIST;
		}
		ar=new ArrayList<Object>(response.length);
		for(Object o:response){
			ar.add(o);
		}
	}

	/**
	 * Setting {@code type} as {@code TABLE}
	 *
	 * @param response Response's two-dimensional array of command
	 */
	public Response(Object[][] response) {
		type = TABLE;
		ar=new ArrayList<Object>(response.length);
		for (Object[] o:response){
			ar.add(new Row<Object>(o));
		}

	}

	/**
	 * @return Data type into this response that may be {@code STRING, TABLE, LIST, NUMBERED_LIST} from this class.
	 */
	public int getType(){
		return type;
	}

	/**
	 * @return an array {@link java.lang.Object Object[]} containing all of the elements in this response in proper sequence
	 */
	public Object[] getResponseArray(){
		return ar.toArray();
	}



	@Override
	public String toString() {
		String str="";
		for(Object a:ar){
			str+=a.toString();
		}
		return str;
	}


}
