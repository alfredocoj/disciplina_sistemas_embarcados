package com.example.ayrton.hihome.bancoDeDados;


public class clienteBanco {

    private String pin;
    private String login;
    private int lampada;
    private int ac;
    private int portao;

    public clienteBanco(String login, int lampada, int ac, int portao){
        this.login = login;
        this.lampada = lampada;
        this.ac = ac;
        this.portao = portao;
    }

    public String getLogin() {
        return login;
    } public void setLogin(String login) {
        this.login = login;
    }

    public int getLampada() {
        return lampada;
    } public void setLampada(int lampada) {
        this.lampada= lampada;
    }

    public int getAc() {
        return ac;
    } public void setAc(int ac) {
        this.ac= ac;
    }

    public int getPortao() {
        return portao;
    } public void setPortao(int portao) {
        this.portao= portao;
    }

}
