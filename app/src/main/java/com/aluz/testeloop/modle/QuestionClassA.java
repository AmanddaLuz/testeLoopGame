package com.aluz.testeloop.modle;

public class QuestionClassA {
    String que;
    String alternativeA, alternativeB, alternativeC, alternativeD, alternativeE;
    String correctAlter;

    public QuestionClassA(String question, String alternativeA, String alternativeB, String alternativeC, String alternativeD, String alternativeE, String correctAlter) {
        this.que = question;
        this.alternativeA = alternativeA;
        this.alternativeB = alternativeB;
        this.alternativeC = alternativeC;
        this.alternativeD = alternativeD;
        this.alternativeE = alternativeE;
        this.correctAlter = correctAlter;
    }

    public String getQue() {
        return this.que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAlternativeA() {
        return this.alternativeA;
    }

    public void setAlternativeA(String alternativeA) {
        this.alternativeA = alternativeA;
    }

    public String getAlternativeB() {
        return this.alternativeB;
    }

    public void setAlternativeB(String alternativeB) {
        this.alternativeB = alternativeB;
    }

    public String getAlternativeC() {
        return this.alternativeC;
    }

    public void setAlternativeC(String alternativeC) {
        this.alternativeC = alternativeC;
    }

    public String getAlternativeD() {
        return this.alternativeD;
    }

    public void setAlternativeD(String alternativeD) {
        this.alternativeD = alternativeD;
    }

    public String getAlternativeE() {
        return this.alternativeE;
    }

    public void setAlternativeE(String alternativeE) {
        this.alternativeE = alternativeE;
    }

    public String getCorrectAlter() {return this.correctAlter;
    }

    public void setCorrectAlter(String correctAlter) {
        this.correctAlter = correctAlter;
    }
}

