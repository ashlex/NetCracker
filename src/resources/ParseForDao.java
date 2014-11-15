package resources;

import java.io.File;

public class ParseForDao {
	File parseFile=null;

	ParseForDao(File file){
		if(file==null){
			throw new IllegalArgumentException("File can not be null");
		}
		this.parseFile=file;
	}

	ParseForDao(String path){
		this.parseFile=new File(path);
	}

}
