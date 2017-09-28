package com.example.ayrton.hihome.bancoDeDados;


public class clienteBanco {

//    private String login;
    private int qtd_lampada;
    private int qtd_ac;
    private int qtd_portao;

    public clienteBanco(){

    }

    public clienteBanco(int qtd_lampada, int qtd_ac, int qtd_portao){
        //this.login = login;
        this.qtd_lampada = qtd_lampada;
        this.qtd_ac = qtd_ac;
        this.qtd_portao = qtd_portao;
    }

//    public String getLogin() {
//        return login;
//    } public void setLogin(String login) {
//        this.login = login;
//    }

    public int getLampada() {
        return qtd_lampada;
    } public void setLampada(int lampada) {
        this.qtd_lampada= lampada;
    }

    public int getAc() {
        return qtd_ac;
    } public void setAc(int ac) {
        this.qtd_ac= ac;
    }

    public int getPortao() {
        return qtd_portao;
    } public void setPortao(int portao) {
        this.qtd_portao= portao;
    }

}
