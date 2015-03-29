package main.dao.file;

import main.dao.entity.Row;
import main.system.Container;
import main.system.IIterator;

import java.io.*;
import java.util.Scanner;

public class Parser implements Container {
	private Scanner is;
	private Row<String> header=null;

	public Parser(File file , boolean header) throws FileNotFoundException {

		is=new Scanner(file);
		if(header){
			if(is.hasNextLine()) {
				this.header = new Row<String>(is.nextLine().split(";"));
			}
		}
	}
	public Parser(File file) throws FileNotFoundException {
		this(file,false);
	}

	public Row<String> getHeader(){
		return header;
	}

	@Override
	public IIterator getIterator() {
		return new ParserIterator();
	}

	private class ParserIterator implements IIterator{

		@Override
		public boolean hasNext() {
			return is.hasNextLine();
		}

		@Override
		public Object next() {

			return new Row<String>(is.nextLine().split(";"));
		}
	}
}
