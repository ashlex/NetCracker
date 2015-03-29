package main.dao.entity;

/**
 * Description data row
 */
public class Row<T>{
	T[] o;

	public Row(T[] o){
		this.o=o;
	}
	public T[] getRow() {
		return (T[])o;
	}
	public int size(){
		return o.length;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Row){
			if (((Row) obj).size()==size()){
				for(int i=0;i<size();i++){
					if(!o[i].equals(((Row) obj).getRow()[i])){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
