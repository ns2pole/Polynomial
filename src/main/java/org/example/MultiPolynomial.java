package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//4a + 5a + 3a^2b^3のような未計算の多項式は含めないとする。
public class MultiPolynomial extends ArrayList<MultiMonomial> {

    public MultiPolynomial(List<MultiMonomial> lm) {
        super(lm);
    }

    public MultiPolynomial plus(MultiPolynomial mp) {
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