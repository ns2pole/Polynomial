package org.example;

import java.util.ArrayList;

public class ArrayListMethodInt {
	/**input ex{4,2,6,5} output ex 17*/
		/**listを一つ一つ取得・足し算して結果を戻す*/
		public static int add(ArrayList<Integer> ints) {
			int output = 0;
			for(int i = 0;i<ints.size();i++) {
					output = output + ints.get(i);
			}
			return output;
		}
		
	/**getAllProductOf([2,2,2,3,5]) -> 120*/
	public static int getAllProductOf(ArrayList<Integer> primeFactors) {
		int number = 1;
		for(int i=0;i<primeFactors.size();i++) {
			number = number * primeFactors.get(i);
		}
		return number;
	}
	
	/**getGCDOf(20,24) -> 4 /**getGCDOf(0,24) -> 1 (正:24)*/
	public static int getGCDOf(int int1,int int2) {
		if(int1 == 0 && int2 != 0) {
			return int2;
		}else if(int2 == 0 && int1 != 0){
			return int1;
		}else if(int1 == 0 && int2 == 0) {
			return 0;
		}else {
			ArrayList<Integer> primeFactors1 = ArrayListMethodInteger.getPrimeFactorsOf(int1);
			ArrayList<Integer> primeFactors2 = ArrayListMethodInteger.getPrimeFactorsOf(int2);
			ArrayList<Integer> GCDList = ArrayListMethod.getIntersectOf(primeFactors1, primeFactors2);
			int GCD = ArrayListMethodInt.getAllProductOf(GCDList);
			return GCD;
		}
		
	}
	
	/**20,24 ->{2,2,5},{2,2,2,3} -> {2,2,2,2,2,3,5} -> {2,2,2,3,5} -> 120*/
	public static int getLCMOf(int int1, int int2){
		if(int1*int2 == 0) {
			throw new IllegalArgumentException
			("0が含まれているので求められません");
		}
		
		ArrayList<Integer> ints1= ArrayListMethodInteger.getPrimeFactorsOf(int1);
		ArrayList<Integer> ints2= ArrayListMethodInteger.getPrimeFactorsOf(int2);
		ArrayList<Integer> LCMArray = ArrayListMethod.getUnionOf(ints1, ints2);
		ArrayList<Integer> intersect = ArrayListMethod.getIntersectOf(ints1, ints2);
		for(int i = 0;i < intersect.size();i++) {
			LCMArray.remove(LCMArray.indexOf(intersect.get(i)));
		}
		int LCM = ArrayListMethodInt.getAllProductOf(LCMArray);
		return LCM;
	}
	
	/**複数の数字からLCM,GCDを計算する（一般化）input30,42,105 -> getGCOf(30,42)...6 -> getGCDOf(6,105)...3 output -> 3*/
	public static int getGCDNumbers(int... integers) {
		ArrayList<Integer> GCDs = new ArrayList<Integer>();
		GCDs.add(0, getGCDOf(integers[0],integers[1]));
		for(int i = 1;i<integers.length;i++) {
			GCDs.add(i,getGCDOf(GCDs.get(i - 1), integers[i]));
		}
		System.out.println(GCDs);
		return GCDs.get(GCDs.size() - 1);
	}
	
	public static int[] clone(int[] int1){
		int[] clone = new int[int1.length];
		for(int i = 0;i<int1.length;i++) {
			clone[i] = int1[i];
		}
		return clone;
		
	}
	
	/**LCMの複数形を可変長引数を使って作る*/
	public static int getLCMNumbers(int... integers) {
		ArrayList<Integer> LCMs = new ArrayList<Integer>();
		LCMs.add(0, getLCMOf(integers[0],integers[1]));
		for(int i = 1;i<integers.length;i++) {
			LCMs.add(i,getLCMOf(LCMs.get(i - 1), integers[i]));
		}
		return LCMs.get(LCMs.size() - 1);
	}
	
	public static int product(ArrayList<Integer> ints) {
		int output = 1;
		for(int i = 0;i<ints.size();i++) {
			output = output * ints.get(i);
		}
		return output;
	}
	
}
