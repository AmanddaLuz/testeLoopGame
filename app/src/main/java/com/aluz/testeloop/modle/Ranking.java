package com.aluz.testeloop.modle;

public class Ranking implements Cloneable{

    private String login;
    private String pontuacao;

    public Ranking(String login, String pontuacao) {
        this.login = login;
        this.pontuacao = pontuacao;
    }

    public Ranking(){

    }
    public Ranking(Ranking r) {
        this.login = r.login;
        this.pontuacao = r.pontuacao;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public Object clone(){
        Ranking clone = new Ranking(this);
        return clone;
    }
}

