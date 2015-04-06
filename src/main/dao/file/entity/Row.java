package main.dao.file.entity;

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

	@Override
	public String toString() {
		StringBuilder s=new StringBuilder();
		for (int i=0;i<o.length;i++) {
			s.append(((T)o[i]).toString());
			s.append(";");
		}
		return s.toString();
	}
}
