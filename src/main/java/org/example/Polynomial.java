package org.example;

import java.util.ArrayList;

public class Polynomial {
    ArrayList<Ratio> coffs;

    Polynomial() {
        this.coffs = new ArrayList<>();
    }

    Polynomial(ArrayList<Ratio> ratios) {
        this.coffs = new ArrayList<>();
        // foreachループでratiosの要素を処理
        for (Ratio ratio : ratios) {
            // ここで個々のratioに対する処理を行う
            // 例えば、内部のリストに追加する場合:
            this.coffs.add(ratio);
        }
    }

    //次数を得る
    private int getDegree() {
        return this.coffs.size() - 1;
    }

    //Pairのうち低次の多項式を返す
    private Polynomial getLowerDegreePolynomial(Pair<Polynomial, Polynomial> pair) {
        if(pair.getFirst().getDegree() <= pair.getSecond().getDegree()) {
            return pair.getFirst();
        } else {
            return pair.getSecond();
        }
    }

    //Pairのうち高次の多項式を返す
    private Polynomial getHigherDegreePolynomial(Pair<Polynomial, Polynomial> pair) {
        if(pair.getFirst().getDegree() >= pair.getSecond().getDegree()) {
            return pair.getFirst();
        } else {
            return pair.getSecond();
        }
    }


    //ex. (1+x+3x^2) + (3+5x^2) -> 4+x+8x^2
    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();
        //次数が同じ場合は何も考えず足していけばいい
        if(this.getDegree() == p.getDegree()) {
            for (int i = 0; i <= this.getDegree(); i++) {
                result.coffs.add(this.coffs.get(i).getAddedRatio(p.coffs.get(i)));
            }
            return result;
        }/*次数が異なる場合は、共通してる部分だけ足して共通してない高次は足さない */ else {
            Pair<Polynomial, Polynomial> pair = new Pair(this, p);
            Polynomial higher = getHigherDegreePolynomial(pair);
            Polynomial lower = getLowerDegreePolynomial(pair);
            for (int i = 0; i <= lower.getDegree(); i++) {
                result.coffs.add(lower.coffs.get(i).getAddedRatio(higher.coffs.get(i)));
            }
            for (int i = lower.getDegree() + 1; i <= higher.getDegree(); i++) {
                result.coffs.add(higher.coffs.get(i));
            }
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        Polynomial p = (Polynomial) o;
        if(this.getDegree() != p.getDegree()) {
            return false;
        } else {
            for (int i = 0; i <= this.getDegree(); i++) {
                if (!this.coffs.get(i).equals(p.coffs.get(i))) {
                    return false;
                };
            }
            return true;
        }
    }

    @Override
    public String toString() {
        String str = "<";
        for (int i = 0; i <= this.getDegree(); i++) {
            if(this.coffs.get(i).isZero()) {
            } else {
                if(i == 0) {
                    str += this.coffs.get(i) + " + ";
                } else if(i == 1) {
                    str += this.coffs.get(i) + " x" + " + ";
                } else {
                    str += this.coffs.get(i) + " x^" + i + " + ";
                }
            }
        }
        str = str.substring(0, str.length() - 3);
        str += ">";

        return str;
    }
}