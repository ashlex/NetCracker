package main.dao.file;

import main.command.ICommand;
import main.dao.IDaoCommandHelp;
import main.system.entity.CommandHelp;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class FileDaoCommandHelp implements IDaoCommandHelp {
	private enum Cools {COMMAND, SYNTAX, SHORT_HELP, FULL_HELP}

	private File commandHelpDF;
	private static final Logger log = Logger.getLogger(FileDaoCommandHelp.class.getName());

	public FileDaoCommandHelp(File commandHelpDF) {
		if (commandHelpDF == null) {
			throw new IllegalArgumentException("Object commandHelpDF is null.");
		}
		this.commandHelpDF = commandHelpDF;
	}

	@Override
	public CommandHelp getHelp(ICommand command) {
		return getHelp(command.getAlias());
	}

	@Override
	public CommandHelp getHelp(String alias) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(commandHelpDF));
			String line;
			String[] row;

			while ((line = reader.readLine()) != null) {
				row = split(line,";");
//				System.out.println(line);
//				System.out.println(row.length);
//				for (int i=0;i<row.length;i++){
//					System.out.print(row[i]+";");
//				}
				if (row[Cools.COMMAND.ordinal()].equalsIgnoreCase(alias)) {
					return new CommandHelp(row[Cools.COMMAND.ordinal()],
							row[Cools.FULL_HELP.ordinal()],
							row[Cools.SHORT_HELP.ordinal()],
							row[Cools.SYNTAX.ordinal()]);
				}
			}
			log.fine("Help for command " + alias + " not found.");
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "FileNotFoundException: ", e);
		} catch (IOException e) {
			log.log(Level.INFO, "IOException: ", e);
		}
		return null;
	}

	@Override
	public ArrayList<CommandHelp> getHelps(String[] aliases) {
		ArrayList<CommandHelp> helps = new ArrayList<CommandHelp>();
		String[] a = aliases.clone();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(commandHelpDF));
			String line;
			String[] row;
			String tmp;
			int k = a.length;
			while ((line = reader.readLine()) != null) {
				row = split(line,";");
				for (int j = 0; j < k; j++) {
					if (a[j].equalsIgnoreCase(row[Cools.COMMAND.ordinal()])) {
						helps.add(new CommandHelp(row[Cools.COMMAND.ordinal()],
										row[Cools.FULL_HELP.ordinal()],
										row[Cools.SHORT_HELP.ordinal()],
										row[Cools.SYNTAX.ordinal()])
						);
						tmp = a[j];
						a[j] = a[--k];
						a[k] = tmp;
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "FileNotFoundException: ", e);
		} catch (IOException e) {
			log.log(Level.INFO, "IOException: ", e);
		}
		return helps;
	}

	@Override
	public ArrayList<CommandHelp> getHelps(ArrayList<ICommand> aliases) {
		return null;
	}

	private String[] split(String str, String regex, int limit) {
		char ch = 0;
		if (((regex.toCharArray().length == 1 &&
				".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) ||
				(regex.length() == 2 &&
						regex.charAt(0) == '\\' &&
						(((ch = regex.charAt(1)) - '0') | ('9' - ch)) < 0 &&
						((ch - 'a') | ('z' - ch)) < 0 &&
						((ch - 'A') | ('Z' - ch)) < 0)) &&
				(ch < Character.MIN_HIGH_SURROGATE ||
						ch > Character.MAX_LOW_SURROGATE)) {
			int off = 0;
			int next = 0;

			boolean limited = false;
			ArrayList<String> list = new ArrayList<String>();
			while ((next = str.indexOf(ch, off)) != -1) {
				if (!limited || list.size() < limit - 1) {
					list.add(str.substring(off, next));
					off = next + 1;
				} else {    // last one
					//assert (list.size() == limit - 1);
					list.add(str.substring(off, str.length()));
					off = str.length();
					break;
				}
			}
			// If no match was found, return this
			if (off == 0)
				return new String[]{str};

			// Add remaining segment
			if (!limited || list.size() < limit)
				list.add(str.substring(off, str.length()));

			// Construct result
			int resultSize = list.size();
			if (limit == 0) {
				if (resultSize > 0 && list.get(resultSize - 1).length() == 0)
					resultSize--;
			}
			String[] result = new String[resultSize];
			return list.subList(0, resultSize).toArray(result);
		}
		return Pattern.compile(regex).split(str, limit);
	}

	private String[] split(String str, String regex){
		return split(str, regex, 0);
	}
}
