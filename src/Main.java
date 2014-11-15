import command.ICommand;

public class Main {
	public static void main(String []args){
		try {
			ICommand command= (ICommand) Class.forName("Login").newInstance();
//			command.execute();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
