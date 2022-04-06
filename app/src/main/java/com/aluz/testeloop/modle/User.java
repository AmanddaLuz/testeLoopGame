package com.aluz.testeloop.modle;

public class User implements Cloneable {

    private int id;
    private String login;
    private String password;
    private String pontuacao;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User(String login, String password, String pontuacao) {
        this.login = login;
        this.password = password;
        this.pontuacao = pontuacao;
    }


    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

    }

    public User(int id, String login, String password, String pontuacao) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.pontuacao = pontuacao;

    }

    public User(User u) {
        this.id = u.id;
        this.login = u.login;
        this.password = u.password;
        this.pontuacao = u.pontuacao;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPontuacao() {return this.pontuacao; }

    public void setPontuacao(String pontuacao) {this.pontuacao = pontuacao;}

    @Override
    public Object clone(){
        User clone = new User(this);
        return clone;
    }
}
