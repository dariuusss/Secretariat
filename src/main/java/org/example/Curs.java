package org.example;

import java.util.ArrayList;

class Curs <TipStudent extends Student>{
    String denumire;
    private int capacitate;

    private int nr_inscrisi;

    private double medie_minima;

    double getMedie_minima() {
        return this.medie_minima;
    }

    void setMedie_minima(double medie_noua) {
        this.medie_minima = medie_noua;
    }

    int getNr_inscrisi() {
        return this.nr_inscrisi;
    }

    void setNr_inscrisi(int nr) {
        this.nr_inscrisi = nr;
    }

    String program_de_studii;

    private ArrayList<TipStudent> studenti_curs = new ArrayList<>();

    ArrayList<TipStudent> getStudenti_curs() {
        return this.studenti_curs;
    }


    Curs() {

    }

    Curs(String denumire,int capacitate,String program_de_studii) {
        this.denumire = denumire;
        this.setCapacitate(capacitate);
        this.nr_inscrisi = 0;
        this.medie_minima = 11;
        this.program_de_studii = program_de_studii;
    }

    int getCapacitate() {
        return this.capacitate;
    }
    void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }
}