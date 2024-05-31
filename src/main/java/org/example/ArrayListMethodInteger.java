package jp.math.program;

import java.util.ArrayList;
import java.util.Collections;

public interface ArrayListMethodInteger {
	//[1,2,3,4] [6,7,8,9] output [1,2,3,4,6,7,8,9]
	public static ArrayList<Integer> getMergedLists(ArrayList<Integer> ints1, ArrayList<Integer> ints2){
		ArrayList<Integer> clone = new ArrayList<Integer>();
		clone.addAll(ints1);
		ArrayList<Integer> interSection = ArrayListMethod.getInterSection(ints1, ints2);
		clone.addAll(ints2);
		clone.removeAll(interSection);
		clone.addAll(interSection);
		Collections.sort(clone);
		return clone;
	}
	//getPrimeFactorsOf(20) -> [2,2,5] //getPrimeFactorsOf(24) -> [2,2,2,3]
	public static ArrayList<Integer> getPrimeFactorsOf(int int1){
	ArrayList<Integer> PrimeFactors = new ArrayList<Integer>();
	int1 = Math.abs(int1);
		for(int i=2;i<=int1;i++) {
			if(int1%i==0) {
				PrimeFactors.add(i);
				int1 = int1 / i;
				i=1;
			}
		}
	return PrimeFactors;
	}
	
	public static ArrayList<Integer> getNoDuplicateListOf(ArrayList<Integer> arr){
		ArrayList<Integer> detectedNum = new ArrayList<Integer>();
		for(int i = 0;i<arr.size();i++) {
			if(!(detectedNum.contains(arr.get(i)))) {
				detectedNum.add(arr.get(i));
			}
		}
		return detectedNum;
	}	
	/**
	 * 何個あるかカウント
	 * [2,2,2,2,2,5,5],2 -> 5
	 *  
	 */
	public static int countNum(ArrayList<Integer> primeFactors,int detectNum) {
		int count = 0;
		for(int i = 0;i<primeFactors.size();i++) {
			if(detectNum == primeFactors.get(i)) {
				count  = count + 1;
			}
		}
		return count;
	}
	
	/**
	 * 与えられた数の半分（奇数だったら小さい方）
	 * 5 -> 2,6 -> 3
	 */
	public static int divideNum(int dividedNum) {
		int result = 0;
		if(dividedNum % 2 <= 0) {
			result = dividedNum / 2;
		}else {
			result = (dividedNum - 1) / 2;
		}
		return result;
	}
	
	/**
	 * 配列に二個あるやつのうち片方を取り出し新しく配列を作る
	 * ex. 18 -> [3]
	 * 200 -> [2,2,2,5,5] -> [2,5]
	 * 800 -> [2,2,2,2,2,5,5] ->[2,2,5}
	 */
	//約数出す->全ての種類の数に対して何個かカウント->それぞれ半分以下の数だけresultNumに移動->resultNum全部かける
	public static ArrayList<Integer> detectDoubleNum (ArrayList<Integer> primeFactors){
		ArrayList<Integer> resultNum = new ArrayList<Integer>(); 
		ArrayList<Integer> noDuplicateList = getNoDuplicateListOf(primeFactors);
		for(int i = 0;i<noDuplicateList.size();i++) {
			int count = divideNum(countNum(primeFactors, noDuplicateList.get(i)));
			for(int l= 0;l<count;l++) {
				resultNum.add(noDuplicateList.get(i));
			}
		}
		
		return resultNum;
	}
	//一個しかない（または奇数）を取り出し
	//約数出す->doubleNumで出したやつを二倍して配列から引く
	public static ArrayList<Integer> detectSingleNum (ArrayList<Integer> primeFactors){
		ArrayList<Integer> doubleNum = detectDoubleNum(primeFactors);
		ArrayList<Integer> doubleDoubleNum = detectDoubleNum(primeFactors);
		ArrayList<Integer> resultNum = (ArrayList<Integer>) primeFactors.clone();
		
		for(int i = 0;i<doubleNum.size();i++) {
			doubleDoubleNum.add(doubleNum.get(i));
		}
		for(int i = 0;i<doubleDoubleNum.size();i++) {
			resultNum.remove(doubleDoubleNum.get(i));
		}
		return resultNum;
	}
	
	//√換算
	//8 -> 2,2,2 -> 2√2
	public static Root toRoot(int int1){
		Ratio zeroRatio = new Ratio(1,0);
		Ratio oneRatio = new Ratio(1,1);
		Root zeroRoot = new Root(zeroRatio,0);
		Root oneRoot = new Root(oneRatio,1);
		
		if(int1 == 0) {
			return zeroRoot;
		}else if(int1 == 1){
			return oneRoot;
		}else{
			ArrayList<Integer> primeFactors = getPrimeFactorsOf(int1);
		Collections.sort(primeFactors);
		ArrayList<Integer> resultCoff = detectDoubleNum(primeFactors);
		Ratio coff = new Ratio(1,1);
		for(int i = 0;i<resultCoff.size();i++) {
			Ratio a = new Ratio(1,resultCoff.get(i));
			coff = coff.getProductRatio(a);
		}
		ArrayList<Integer> resultInside = detectSingleNum(primeFactors);
		int inside = 1;
			for(int i = 0;i<resultInside.size();i++) {
			int a = resultInside.get(i);
			inside = inside * a;
		}
		Root result = new Root(coff,inside);
		
			return result;
		}
	}	
		
	//public static Root toRoot(Ratio ratio) {
		//return 0;
		//}
	
	public static IntRoot toIntRoot(int int1){
		int zeroRatio = 0;
		int oneRatio = 1;
		IntRoot zeroRoot = new IntRoot(zeroRatio,0);
		IntRoot oneRoot = new IntRoot(oneRatio,1);
		
		if(int1 == 0) {
			return zeroRoot;
		}else if(int1 == 1){
			return oneRoot;
		}else{
			ArrayList<Integer> primeFactors = getPrimeFactorsOf(int1);
		Collections.sort(primeFactors);
		ArrayList<Integer> resultCoff = detectDoubleNum(primeFactors);
		int coff = 1;
		for(int i = 0;i<resultCoff.size();i++) {
			int a = resultCoff.get(i);
			coff = coff * a;
		}
		ArrayList<Integer> resultInside = detectSingleNum(primeFactors);
		int inside = 1;
			for(int i = 0;i<resultInside.size();i++) {
			int a = resultInside.get(i);
			inside = inside * a;
		}
		IntRoot result = new IntRoot(coff,inside);
		
			return result;
		}
	}	
	
	//√4 -> 2 √9 -> 3
	public static int toInt(Root root) {
		ArrayList<Integer> primeFactors = getPrimeFactorsOf(root.getInsider());
		ArrayList<Integer> half = detectDoubleNum(primeFactors);
		int result = 1;
		for(int i = 0;i<half.size();i++) {
			result = result * half.get(i);
		}
		return result;
	}
	
	public static int toInt2(IntRoot root) {
		ArrayList<Integer> primeFactors = getPrimeFactorsOf(root.getInsider());
		ArrayList<Integer> half = detectDoubleNum(primeFactors);
		int result = 1;
		for(int i = 0;i<half.size();i++) {
			result = result * half.get(i);
		}
		return result;
	}
	
	
	//有理化
	//1/√2 -> √2/2  2/3√3 -> 2√3/9
	public static IntRootRatio rationalizeOf(IntRootRatio ratio1) {
		IntRoot denominator = ratio1.getDenominator();
		IntRoot numerator = ratio1.getNumerator();
		IntRoot rootDenominator = denominator.getProductedRoot(toIntRoot(denominator.getInsider()));
		IntRoot rootNumerator = numerator.getProductedRoot(toIntRoot(denominator.getInsider()));
		IntRootRatio result = new IntRootRatio(rootDenominator,rootNumerator);
		if(ArrayListMethod.isCoprime(rootDenominator.getCoff(), rootNumerator.getCoff())) {
			return result;
		}else {
			Ratio coffRatio = new Ratio(rootDenominator.getCoff(),rootNumerator.getCoff());
			Ratio irrRatio = coffRatio.getIrreducibleRatio();
			IntRoot resultDenominator = new IntRoot(irrRatio.getDenominator(),rootDenominator.getInsider());
			IntRoot resultNumerator = new IntRoot(irrRatio.getNumerator(),rootNumerator.getInsider());
			IntRootRatio irrResult = new IntRootRatio(resultDenominator,resultNumerator);
			return irrResult;
		}
	}
	
	//配列のstring化(ジェネリクス使おう)
	public static String arrayToString(ArrayList<Integer> arr) {
		Integer resultInteger = arr.get(0);
		String result = Integer.toString(resultInteger);
		for(int i = 1;i < arr.size();i++) {
			result = result + Integer.toString(arr.get(i));
		}
		return result;
	}
}