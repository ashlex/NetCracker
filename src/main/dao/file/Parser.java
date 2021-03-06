package main.dao.file;

import main.dao.file.entity.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
	private File file;
	private static final Logger log = Logger.getLogger(Parser.class.getName());
	private Row<String> header = null;

	private boolean isHeader = false;

	/**
	 * @param file   file for parse.
	 * @param header {@code true} if this file content header or {@code false} if not content.
	 */
	public Parser(File file, boolean header) {
		this.file = file;
		isHeader = header;
	}

	public Parser(File file) throws FileNotFoundException {
		this(file, false);
	}

	public Row<String> getHeader() {
		return header;
	}

	public ArrayList<Row> getRows() {
		ArrayList<Row> rows = new ArrayList<Row>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String str;
			if (isHeader) {
				if ((str = bufferedReader.readLine()) != null) {

					header = new Row<String>(processReadLines(str.split(";")));
				}
			}
			while ((str = bufferedReader.readLine()) != null) {
				rows.add(new Row<String>(processReadLines(str.split(";"))));
			}
			bufferedReader.close();

		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, "", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
		}
		return rows;
	}

	public boolean write(ArrayList<Row> list) {
		try {
			FileWriter fileWriter = new FileWriter(file, false);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			if (isHeader) {
				printWriter.println(header.toString());
			}
			for (Row<String> row : list) {
				processWriteLines(row.getRow());
				printWriter.println(row.toString());
			}
			printWriter.flush();
			printWriter.close();
			return true;
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
			return false;
		}
	}

	private String[] processReadLines(String[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i].startsWith("\"") && s[i].endsWith("\"")) {
				s[i] = s[i].substring(1, s[i].length() - 1);
			}
		}
		return s;
	}

	private String[] processWriteLines(String[] s) {
		for (int i = 0; i < s.length; i++) {
			StringBuilder sb=new StringBuilder("\"");
			sb.append(s[i]);
			sb.append("\"");
			s[i] = sb.toString();
		}
		return s;
	}
}
