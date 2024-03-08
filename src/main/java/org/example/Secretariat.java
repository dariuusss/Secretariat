package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class Secretariat {

    private ArrayList<Student> studenti = new ArrayList<>();

    ArrayList<Student> getStudenti() {
        return this.studenti;
    }

    private ArrayList<Curs> cursuri = new ArrayList<>();

    ArrayList<Curs> getCursuri() {
        return this.cursuri;
    }

    Secretariat() {

    }

    void adauga_student(String nume_student, String program_de_studii) {
        Student student = null;
        if (program_de_studii.equals("licenta"))
            student = new StudentLicenta(nume_student);
        else if (program_de_studii.equals("master"))
            student = new StudentMaster(nume_student);
        this.studenti.add(student);
    }

    int verifica_student_duplicat(String name) {//returneaza 1 daca exista deja un student cu numele acesta
        for (int i = 0; i < studenti.size(); i++)
            if (studenti.get(i).nume.equals(name))
                return 1;
        return 0;
    }

    void adauga_curs(String program_de_studii, String nume_curs, int capacitate) {
        Curs curs = new Curs(nume_curs, capacitate, program_de_studii);
        this.cursuri.add(curs);
    }

    void asigneaza_nota(String nume_student, double medie) {
        for (int i = 0; i < this.studenti.size(); i++)
            if (this.studenti.get(i).nume.equals(nume_student)) {
                this.studenti.get(i).setMedie(medie);
                break;
            }
    }


    void afiseaza_medii(FileWriter f2, BufferedWriter s) {
        try {
            s.write("***");
            s.newLine();
            for (int i = 0; i < this.studenti.size(); i++) {
                s.write(this.studenti.get(i).nume + " - " + this.studenti.get(i).getMedie());
                s.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void solutioneaza_contestatie(String nume_student, double medie_noua) {
        for (int i = 0; i < this.studenti.size(); i++)
            if (this.studenti.get(i).nume.equals(nume_student)) {
                this.studenti.get(i).setMedie(medie_noua);
                break;
            }
    }

    void adauga_preferinta(Student q, String nume_curs_preferat) {

        for (int j = 0; j < this.cursuri.size(); j++)
            if (this.cursuri.get(j).denumire.equals(nume_curs_preferat)) {
                q.getListaPreferinte().add(this.cursuri.get(j));
                break;
            }


    }

    void repartizeaza() {

        Student stud_aux = null;
        Curs curs_aux = null;
        for (int m = 0; m < this.studenti.size(); m++) { //parcurg studentii

            stud_aux = this.studenti.get(m);
            for (int n = 0; n < stud_aux.getListaPreferinte().size(); n++) { //parcurg preferintele
                curs_aux = stud_aux.getListaPreferinte().get(n);
                if ((stud_aux instanceof StudentLicenta && curs_aux.program_de_studii.equals("licenta")) ||
                        (stud_aux instanceof StudentMaster && curs_aux.program_de_studii.equals("master"))) { //nu pot asigna licenta la master sau invers


                    int nr_inscrisi = curs_aux.getNr_inscrisi();
                    int capacitate = curs_aux.getCapacitate();
                    if (nr_inscrisi < capacitate) {
                        curs_aux.getStudenti_curs().add(stud_aux);
                        stud_aux.getCursuri().add(curs_aux);
                        stud_aux.setCurs_asignat(1);
                        if (stud_aux.getMedie() < curs_aux.getMedie_minima())
                            curs_aux.setMedie_minima(stud_aux.getMedie());
                        curs_aux.setNr_inscrisi(curs_aux.getNr_inscrisi() + 1);
                        break;
                    } else if (nr_inscrisi == capacitate) {

                        if (curs_aux.getMedie_minima() == stud_aux.getMedie()) {
                            curs_aux.getStudenti_curs().add(stud_aux);
                            stud_aux.getCursuri().add(curs_aux);
                            stud_aux.setCurs_asignat(1);
                            curs_aux.setMedie_minima(stud_aux.getMedie());
                            break;
                        }
                    }

                }

            }
        }

    }

    void posteaza_curs(String nume_curs, FileWriter f2, BufferedWriter s) {
        try {
            s.write("***");
            s.newLine();
            s.write(nume_curs + " (");
            Curs c = null;
            for (int i = 0; i < this.cursuri.size(); i++)
                if (this.cursuri.get(i).denumire.equals(nume_curs)) {
                    c = this.cursuri.get(i);
                    break;
                }

            Collections.sort(c.getStudenti_curs(), new ClasaDeComparare() {
                @Override
                public int compare(Student a, Student b) {
                    if(a.nume.compareTo(b.nume) > 0)
                        return 1;
                    return -1;
                }
            });

            s.write(c.getCapacitate() + ")");
            s.newLine();
            Student aux = null;
            for(int i = 0;i < c.getStudenti_curs().size();i++) {
                if(c.getStudenti_curs().get(i) instanceof StudentLicenta)
                    aux = (StudentLicenta)c.getStudenti_curs().get(i);
                else
                    aux = (StudentMaster)c.getStudenti_curs().get(i);
                s.write(aux.nume + " - " + aux.getMedie());
                s.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void posteaza_student(String nume_student,FileWriter f2, BufferedWriter s) {
        try {
            s.write("***");
            s.newLine();
            s.write("Student ");
            Student acs_stud = null;
            String program_studii = null;
            for(int i = 0;i < this.studenti.size();i++)
                if(this.studenti.get(i).nume.equals(nume_student)) {
                    acs_stud = this.studenti.get(i);
                    break;
                }

            if(acs_stud instanceof StudentLicenta)
                program_studii = "Licenta";
            else
                program_studii = "Master";
            s.write(program_studii + ": " + nume_student + " - ");
            s.write(acs_stud.getMedie() + " - " + acs_stud.getCursuri().get(0).denumire);
            s.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}