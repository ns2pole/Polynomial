package jp.math.program;
import java.util.ArrayList;
public class ArrayListMethod {
	/**[1,4,4,5,10],[1,5,12,15] output [1,5]*/
	public static <T> ArrayList<T> getInterSection(ArrayList<T> ints1, ArrayList<T> ints2){
		ArrayList<T> clone = new ArrayList<T>();
		clone.addAll(ints1);
		clone.retainAll(ints2);
		return clone;
	}
	
	/**input[2,2,5,5],[2,3,5,5] output[2,5,5]
	*一つ目の配列の一個目と二つ目の配列の一個目、二個目,,,
	*一つ目の配列の二個目・・・と二つ目の配列の一個目、二個目,,,
	*同じなら一つ配列に入れ、違ったら両方排除*/
	public static <T> ArrayList<T> getInterSectionOf(ArrayList<T> ints1, ArrayList<T> ints2){
		ints1.sort(null);
		ints2.sort(null);
		ArrayList<T> setDifference = new ArrayList<T>();
		for(int i=0;i<ArrayListMethod.getShorterArray(ints1, ints2).size();i++) {
				if(ints1.get(i) == ints2.get(i)) {
						setDifference.add(ints1.get(i));
				}
			}
		return setDifference;
	}
	
	public static <T> ArrayList<T> getShorterArray(ArrayList<T> arrayParts1, ArrayList<T> arrayParts2){
		if(arrayParts1.size()<arrayParts2.size()) {
			return arrayParts1;
		}else if(arrayParts1.size()>arrayParts2.size()) {
			return arrayParts2;
		}else {
			return arrayParts1;
		}
	}
	
	public static <T> ArrayList<T> getUnionOf(ArrayList<T> ints1, ArrayList<T> ints2){
		ArrayList<T> union = new ArrayList<T>();
		for(int i = 0;i < ints1.size();i++) {
				union.add(ints1.get(i));
		}
		for(int i = 0;i < ints2.size();i++) {
				union.add(ints2.get(i));
		}
		return union;
	}
	
	public static <T> ArrayList<T> getIntersectOf(ArrayList<T> ints1, ArrayList<T> ints2){
		ArrayList<T> intersect = new ArrayList<T>();
		ArrayList<T> numberList = ArrayListMethod.getContainThingOf(ints1, ints2);
		for(int i = 0;i <numberList.size();i++) {
			intersect.addAll(ArrayListMethod.getShorterPartOf(numberList.get(i), ints1, ints2));
		}
		return intersect;
	}
	
	/**互いに素*/
	protected static boolean isCoprime(int ints1,int ints2) {
		if(ArrayListMethodInt.getGCDOf(ints1, ints2) == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**{2,2,5},{2,2,2,3} -> {2,3,5},ArrayList<T>*/
		public static <T> ArrayList<T> getContainThingOf(ArrayList<T> arr1, ArrayList<T> arr2){
			ArrayList<T> numberList = new ArrayList<T>();
			for(int i = 0;i < arr1.size();i++) {
				if(!numberList.contains(arr1.get(i))) {
					numberList.add(arr1.get(i));
				}
			}
			for(int i = 0;i < arr2.size();i++) {
				if(!numberList.contains(arr2.get(i))) {
					numberList.add(arr2.get(i));
				}
			}
			return numberList;
		}
			
		/**2,{2,2,5},{2,2,2,3} -> {2,2},{2,2,2} -> {2,2} 5,{2,2,5},{2,2,2,3} -> {5},{} -> {}*/
		public static <T> ArrayList<T> getShorterPartOf(T number, ArrayList<T> lists1, ArrayList<T> lists2){
			ArrayList<T> arrayParts1 = new ArrayList<T>();
			ArrayList<T> arrayParts2 = new ArrayList<T>();
			for(int i = 0;i < lists1.size();i++) {
				if(number == lists1.get(i)) {
					arrayParts1.add(lists1.get(i));
				}
			}
			for(int i = 0;i < lists2.size();i++) {
				if(number == lists2.get(i)) {
					arrayParts2.add(lists2.get(i));
				}
			}
			ArrayList<T> arrayPart = ArrayListMethod.getShorterArray(arrayParts1, arrayParts2);
			return arrayPart;
		}
			
	public static <T> ArrayList<T> getIntersect(ArrayList<T> arrlist1,ArrayList<T> arrlist2){
		ArrayList<T> intersect = new ArrayList<T>();
		ArrayList<T> numberList = ArrayListMethod.getContainThingOf(arrlist1, arrlist2);
		for(int i = 0;i < numberList.size();i++) {
			intersect.addAll(ArrayListMethod.getShorterPartOf(numberList.get(i), arrlist1, arrlist2));
		}
		return intersect;
	}
	
}