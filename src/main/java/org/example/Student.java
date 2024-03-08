package org.example;

import java.util.ArrayList;

class Student {
    String nume;
    private double medie;

    private int curs_asignat;

    int getCurs_asignat() {
        return this.curs_asignat;
    }

    void setCurs_asignat(int val) {
        this.curs_asignat = val;
    }

     private ArrayList<Curs> cursuri = new ArrayList<>(); //cursurile unde e repartizat

    ArrayList<Curs> getCursuri() {
        return this.cursuri;
    }



     private ArrayList<Curs> lista_preferinte = new ArrayList<>(); //preferintele pentru repartizare

    ArrayList<Curs> getListaPreferinte() {
        return this.lista_preferinte;
    }


    private int nr_cursuri;

    int getNr_cursuri() {
        return this.nr_cursuri;
    }

    void setNr_cursuri(int nrCursuri) {
        this.nr_cursuri = nrCursuri;
    }

    double getMedie() {
        return this.medie;
    }

    void setMedie(double media) {
        this.medie = media;
    }

    Student() {

    }
    Student(String nume) {
        this.nume = nume;
        this.nr_cursuri = 0;
        this.curs_asignat = 0; //initial nu e inscris la vreun curs
    }

}