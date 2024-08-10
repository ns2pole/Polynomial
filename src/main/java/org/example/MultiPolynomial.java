package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//4a + 5a + 3a^2b^3のような未計算の多項式は含めないとする。
//多変数単項式のリストとして多変数多項式を表す。
public class MultiPolynomial extends ArrayList<MultiMonomial> {


    public MultiPolynomial(List<MultiMonomial> lm) {
        //親クラスのコンストラクタを使うという意味
        super(lm);
    }

    public MultiPolynomial plus(MultiPolynomial mp) {
        //方針:
        //1.thisとmpをクローンする(thisClone,mpClone)
        //2.変数部分が共通している所をresultに追加して、
        //3.変数部分が共通している所について、thisClone,mpCloneから削除
        //3.thisClone,mpCloneをresultに追加
        // 以上の手続きで足し算ができる。

        //ex.(1/2 * a^2 + 2/3 * a^2c^1) +
        //(1/4 * a^2 + 4/3 * a^1b^2)
        //(3/4 * a^2 + 2/3 * a^2c^1 + 4/3 * a^1b^2)
        //であればa^2については共通しているので足し算し、この結果3/4 * a^2をresultに追加
        //thisClone,mpCloneそれぞれについてa^2の項について削除
        //thisCloneは(2/3 * a^2c^1)となる。
        //mpCloneは(4/3 * a^1b^2)となる。
        //thisClone,mpCloneそれぞれをresultに追加。
        //以上で、(3/4 * a^2 + 2/3 * a^2c^1 + 4/3 * a^1b^2)が生成される。


        List<MultiMonomial> lm = Arrays.asList();
        MultiPolynomial result = new MultiPolynomial(lm);
        MultiPolynomial thisClone = this.clone();
        MultiPolynomial mpClone = mp.clone();
        for(int i = 0; i < this.size(); i++) {
            for (int j = 0; j < mp.size(); j++) {
                if (this.get(i).variablePart.equals(mp.get(j).variablePart)) {
                    Ratio r = this.get(i).coff.getAddedRatio(mp.get(j).coff);
                    MultiMonomial m = new MultiMonomial(r, this.get(i).variablePart);
                    result.add(m);
                    thisClone.remove(this.get(i));
                    mpClone.remove(mp.get(j));
                }
            }
        }
        for(int i = 0; i < thisClone.size(); i++) {
            result.add(thisClone.get(i));
        }
        for(int i = 0; i < mpClone.size(); i++) {
            result.add(mpClone.get(i));
        }
        return result;
    }

    @Override
    public MultiPolynomial clone() {
        MultiPolynomial result = new MultiPolynomial(Arrays.asList());
        for(int i = 0; i < this.size(); i++) {
            result.add(this.get(i));
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < this.size(); i++) {
            result += this.get(i);
            result += " + ";
        }
        return result.substring(0, result.length() - 2);
    }
}