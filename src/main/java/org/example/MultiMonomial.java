package org.example;

import java.util.List;

public class MultiMonomial {

    //「3a^4」や「3a^4b^2」や「3」など多変数の単項式を表すクラス
    Ratio coff;
    List<Pair<Character, Ratio>> variablePart;

    MultiMonomial(Ratio coff, Pair<Character, Ratio>... variableParts) {
        this.coff = coff;
        for (int i = 0; i < variableParts.length; i++) {
            this.variablePart.add(variableParts[i]);
        }
    }

    MultiMonomial(Ratio coff, List<Pair<Character, Ratio>> variablePart) {
        this.coff = coff;
        this.variablePart = variablePart;
    }

//    public MultiMonomial product(MultiMonomial m) {
//        MultiMonomial result;
//        Ratio resultCoff = this.coff.getProductRatio(m.coff);
//        for()
//        List<Pair<Character, Ratio>> resultVariablePart
//                =
//
//        return
//    }

    @Override
    public boolean equals(Object o) {
        MultiMonomial m = (MultiMonomial) o;
        if (!this.coff.equals(m.coff)) return false;
        if (!this.variablePart.equals(m.variablePart)) return false;
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        result += this.coff.toString();
        for (int i = 0; i < variablePart.size(); i++) {
            result += this.variablePart.get(i).getFirst();
            result += "^";
            result += this.variablePart.get(i).getSecond();
        }
        return result;
    }

}