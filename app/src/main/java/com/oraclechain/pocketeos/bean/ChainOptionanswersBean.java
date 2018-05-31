package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/6.
 */

public class ChainOptionanswersBean {
    /**
     * A : 成吉思汗
     * B : 毛泽东
     * C : 拿破仑
     */

    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;

    public ChainOptionanswersBean(String a, String b, String c, String d, String e, String f) {
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }
}
