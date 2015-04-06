package main.user.command;

import main.command.AbstractCommandBase;
import main.command.entity.ExecuteResult;

import java.io.IOException;
import java.util.Locale;

public class Language extends AbstractCommandBase {

	public Language(){
		this("locale");
	}

	public Language(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		Locale locale=getLocale();
		if(locale!=null) {
			context.setLocale(locale);
		}
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}

	private Locale getLocale() {
//		switch (attributes.size()){
//			case 1:
//				if (attributes.get(0).matches("([a-zA-Z]{2,8})[_-]([a-zA-Z]{2}|[0-9]{3})")) {
//					String str[] = attributes.get(0).split("[_-]]");
//					return new Locale(str[0], str[1]);
//				} else if (attributes.get(0).matches("([a-zA-Z]{2,8})")) {
//					return new Locale(attributes.get(0));
//				}
//				return null;
//			case 2:
//				if (attributes.get(0).matches("[a-zA-Z]{2,8}")) {
//					if (attributes.get(1).matches("[a-zA-Z]{2}|[0-9]{3}")) {
//						return new Locale(attributes.get(0), attributes.get(1));
//					}
//				}
//				return null;
//			default:
//				return null;
//		}
return null;
	}
}
