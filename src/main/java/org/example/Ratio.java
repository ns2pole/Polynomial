package jp.math.program;

import static jp.math.program.ArrayListMethodInteger.*;

import java.util.ArrayList;


public class Ratio {
	private int denominator;
	private int numerator;
	Ratio(int den,int nume){
		this.denominator = den;
		this.numerator = nume;
	}
	
	protected int getDenominator() {
		return this.denominator;
	}
	protected int getNumerator() {
		return this.numerator;
	}
	
	protected void displayDenominator() {
		System.out.println(this.denominator);
	}
	
	protected static int getFieldNum() {
		return 2;
	}
	
	protected Ratio add(Ratio ratio) {
		int resultDenominator = this.denominator + ratio.denominator;
		int resultNumerator = this.numerator + ratio.numerator;
		return new Ratio(resultDenominator, resultNumerator);
	}
	/**約分*/
	protected Ratio getIrreducibleRatio() {
		int GCD = ArrayListMethodInt.getGCDOf(denominator, numerator);
		int newDenominator = this.denominator / GCD;
		int newNumerator = this.numerator / GCD;
		if(newDenominator < 0) {
			return new Ratio(- newDenominator,- newNumerator);
		}else {
			return new Ratio(newDenominator,newNumerator);
		}
	}
	/**掛け算*/
	protected Ratio getProductRatio(Ratio ratio) {
		int multipliedDenominator = this.denominator * ratio.denominator;
		int multipliedNumerator = this.numerator * ratio.numerator;
		Ratio multipliedRatio = new Ratio(multipliedDenominator,multipliedNumerator);
		return multipliedRatio.getIrreducibleRatio();
	}
	/**足し算*/
	protected Ratio getAddedRatio(Ratio ratio) {
		int addedDenominator = this.denominator * ratio.denominator;
		int firstNumerator = this.numerator * ratio.denominator;
		int secondNumerator = this.denominator * ratio.numerator;
		int addedNumerator = firstNumerator + secondNumerator;
		Ratio addedRatio = new Ratio(addedDenominator,addedNumerator);
		return addedRatio.getIrreducibleRatio();
	}
	/**既約であるならばtrueを返す:(12,9) false*/
	protected boolean isIrreducible(){
		int GCD = ArrayListMethodInt.getGCDOf(denominator, numerator);
		if(GCD == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
	
	@Override
	public boolean equals(Object o) {
		Ratio ratio = (Ratio) o;
		if(this.denominator == ratio.denominator && this.numerator == ratio.numerator) {
			return true;
		}else {
			return false;
		}
	}
	
	/**割り算*/
	protected Ratio divide (Ratio ratio) {
		int dividedDenominator = this.denominator * ratio.numerator;
		int dividedNumerator = this.numerator * ratio.denominator;
		Ratio dividedRatio = new Ratio(dividedDenominator,dividedNumerator);
		return dividedRatio.getIrreducibleRatio();
	}
	
	/**引き算*/
	protected Ratio minus (Ratio ratio) {
		int minusedDenominator = this.denominator * ratio.denominator;
		int firstNumerator = this.numerator * ratio.denominator;
		int secondNumerator = this.denominator * ratio.numerator;
		int minusedNumerator = firstNumerator - secondNumerator;
		Ratio minusedRatio = new Ratio(minusedDenominator,minusedNumerator);
		return minusedRatio.getIrreducibleRatio();
	}
	
	/**帯分数へ変換*/
	protected MixedFraction toMixedFraction() {
		int resultNumerator = this.numerator % this.denominator;
		int resultFraction = this.numerator / this.denominator;
		return new MixedFraction(resultFraction,this.denominator,resultNumerator);
	}
	//分母分子に√ -> 有理化
	protected IntRootRatio toRoot() {
		IntRootRatio rootRatio = new IntRootRatio(toIntRoot(this.denominator),toIntRoot(this.numerator));
		IntRootRatio resultRatio = rationalizeOf(rootRatio);
		return resultRatio;
	}
	
	protected double toDesimal() {
		return (Double) null;
	}
	//toDesimal:小数点以下まで平方根を出す 54321 -> 233...
	
	//二桁を一桁で割った商
	//num1 = 割る数
	protected int getQuotient(int num1) {
		
		return 0;
	}
	
	//3,7 ->30 / 7 -> 4
	protected  static int timeAndDivideAnswer(int num1, int num2) {
		int result = num1 * 10 / num2;
		return result;
	}
	
	//3,7 ->30 / 7 -> 2
	protected int timeAndDivideRemainder(int num1, int num2) {
		int result = num1 * 10 % num2;
		return result;
	}
	
	//3,7 -> false 4,5 ->true
	protected boolean hasOnlyTwoOrFive(int num1) {
		ArrayList<Integer> primeFactors = ArrayListMethodInteger.getPrimeFactorsOf(num1);
		for(int i = 0;i < primeFactors.size();i++) {
			if(primeFactors.get(i) != 2 && primeFactors.get(i) != 5) {
				return false;
			}
		}
		return true;
	}
	
	
	//わる→商と余りをほぞんする→余りを割る
	protected Decimal getDecimal(int digitNum) {
		int numerator = this.numerator;
		int denominator = this.denominator;
		int remainder = numerator % denominator;
		ArrayList<Integer> decimals = new ArrayList<Integer>();
		int integerVal = numerator / denominator;
		for(int i = 0;i < digitNum;i++) {
			decimals.add(timeAndDivideAnswer(remainder, denominator));
			remainder = timeAndDivideRemainder(remainder, denominator);
		}
		String decimalValString = ArrayListMethodInteger.arrayToString(decimals);
		String integerValString = Integer.toString(integerVal);
		Decimal result = new Decimal(integerValString, decimalValString);
		return result;
	}
	
	protected String getRecurringDecimal() {
		int remainder = this.numerator % this.denominator;
		int firstRemainder = this.numerator % this.denominator;
		ArrayList<Integer> decimals = new ArrayList<Integer>();
		
		decimals.add(timeAndDivideAnswer(remainder, this.denominator));
		remainder = timeAndDivideRemainder(remainder, this.denominator);
		
		//分母が2と5の時のみ割り切れるので空を返す
		if(hasOnlyTwoOrFive(denominator) == true) {
			return "";
		}else {
			while(remainder != firstRemainder) {
			decimals.add(timeAndDivideAnswer(remainder, denominator));
			remainder = timeAndDivideRemainder(remainder, denominator);
		}
		String result = ArrayListMethodInteger.arrayToString(decimals);
		return result;
		}
	}
	
	//逆数を求める
	protected Ratio getInverse() {
		int denominator = this.getNumerator();
		int numerator = this.getDenominator();
		Ratio num = new Ratio(denominator,numerator);
		return num;
	}
	
	//0かどうか
	protected boolean isZero() {
		if(this.numerator == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//与えられた分数の整数.5を○○/2の形で生成
	//3/4 -> 1/2 53/7(7.5714...) ->15/2
	protected Ratio getNearHalf() {
		Decimal d1 = this.getDecimal(1);
		int integral = Integer.parseInt(d1.getIntegralValue());//割った値を精密に出すメソッドの整数だけ抽出
		Ratio result = new Ratio(2, 2*integral + 1);
		return result;
		
	}
	//四捨五入
	protected int roundOff() {
		Ratio nearHalf = this.getNearHalf();
		Ratio newThis = new Ratio(this.denominator * nearHalf.denominator, this.numerator * nearHalf.denominator);
		Ratio newNearHalf = new Ratio(this.denominator * nearHalf.denominator, nearHalf.numerator * this.denominator);
		if(newThis.numerator < newNearHalf.numerator) {
			int result = (nearHalf.numerator - 1) / 2;
			return result;
		}else /*if(newThis.numerator >= newNearHalf.numerator)*/ {
			int result = (nearHalf.numerator + 1) / 2;
			return result;
		}
	}
}
