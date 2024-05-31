package org.example;

public class Pair<T,S> {
	private T first;
	private S second;
	
	Pair(T first,S second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean equals(Object o) {
		Pair<T,S> p1 = (Pair<T,S>) o;
		if(this.first.equals(p1.first) && this.second.equals(p1.second)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + this.first + "," + this.second + ")";
	}
	
	protected T getFirst(){
		return this.first;
	}
	
	protected S getSecond(){
		return this.second;
	}
}
