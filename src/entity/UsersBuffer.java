package entity;

import java.util.ArrayList;

/**
 * класс одиночка для передачи ссылок на объекты пользователей при аутентификации
 * хранит ссылки на уже созданные объекты пользователей
 * сюда помещается ссылка на каждый созданный объект пользователя
 * при операции LogOut вызвать метод pop() для удаления ссылки на объект из буфера
 */
public class UsersBuffer {
	private static final UsersBuffer BUFFER=new UsersBuffer();
	private static ArrayList<User> users=new ArrayList<User>();

	/**
	 * Записывает ссылку на объект {@link entity.User} в буфер
	 * @param u {@link entity.User}
	 */
	public static void push(User u){
		users.add(u);
//		System.out.println("push begin");
//		for (User user : users) {
//			System.out.println(user);
//		}
//		System.out.println("push end");
	}

	/**
	 * Возращает ссылку на объект {@Link entity.User} и удаляет её из буфера
	 * @param id идентификатор объекта
	 * @return {@link entity.User}
	 */
	public static User pop(long id){
		User u=null;
		for (User user : users) {
			if(user.getId()==id) {
				u=user;
				break;
			}
		}
		if (u!=null){
			users.remove(u);
		}
//		System.out.println("pop begin");
//		for (User user : users) {
//			System.out.println(user);
//		}
//		System.out.println("pop end");
		return u;
	}

}
