package main.system.entity;


public class Topic {
	private final int id;
	private final String header;
	private final String description;
	private int userId;

	public Topic(String header) {
		this(-1, header, null);
	}

	public Topic(int id, String header) {
		this(id, header, null);
	}

	public Topic(int id, String header, String description) {
		if (header == null) {
			throw new IllegalArgumentException("Header can't be null.");
		}
		this.header = header;
		if(id>0) {
			this.id = id;
		}else {
			this.id=-1;
		}
		this.userId=0;
		this.description = description;
	}
	public Topic(int id, String header, String description, int userId) {
		if (header == null) {
			throw new IllegalArgumentException("Header can't be null.");
		}
		this.header = header;
		if(id>0) {
			this.id = id;
		}else {
			this.id=-1;
		}
		if(userId>0) {
			this.userId = userId;
		}else {
			this.userId=0;
		}
		this.description = description;
	}

	public String getHeader() {
		return header;
	}
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "["+id+";"+header+";"+description+"] ";
	}

	@Override
	public boolean equals(Object obj) {
		Topic topic;
		if(obj instanceof Topic){
			topic=(Topic)obj;
			if(topic.getId()==getId()){
				if(topic.getHeader().equals(getHeader())){
					if(topic.getDescription().equals(getDescription())){
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}