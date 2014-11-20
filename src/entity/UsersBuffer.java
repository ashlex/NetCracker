package entity;

/**
 * класс одиночка для передачи ссылок на объекты пользователей при аутентификации
 * хранит ссылки на уже созданные объекты пользователей
 * сюда помещается ссылка на каждый созданный объект пользователя
 * при операции LogOut вызвать метод pop() для удаления ссылки на объект из буфера
 */
public class UsersBuffer {
	private static final UsersBuffer BUFFER=new UsersBuffer();
	User user=null;
	public static UsersBuffer getInstance(){
		return BUFFER;
	}
	public User create(){
		this.user=new User();
		return this.user;
	}
	public User getUser(){
		return this.user;
	}
}
