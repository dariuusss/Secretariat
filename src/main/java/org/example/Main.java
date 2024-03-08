package org.example;
import java.io.*;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String fisier_comenzi = "src/main/resources/" + args[0] + "/" + args[0] + ".in";
        String fisier_output = "src/main/resources/" + args[0] + "/" + args[0] + ".out";
        String comanda;
        FileReader f = null;
        FileWriter f2 = null;
        BufferedReader r = null;
        BufferedWriter s = null;
        int space_index;
        Secretariat secretariat = new Secretariat();
        System.out.println(args[0]);
        try {
            f = new FileReader(fisier_comenzi);
            r = new BufferedReader(f);
            f2 = new FileWriter(fisier_output);
            s = new BufferedWriter(f2);
            String linie;
            do {
                linie = r.readLine();
                if (linie != null) {
                    space_index = linie.indexOf(' ');
                    if(space_index != -1)
                        comanda = linie.substring(0,space_index);
                    else
                        comanda = linie;

                    if(comanda.equals("adauga_student")) {
                        String program_de_studii = linie.substring(space_index + 3);
                        String nume_student;
                        space_index = program_de_studii.indexOf(' ');
                        nume_student = program_de_studii.substring(space_index + 3); //numele studentului
                        program_de_studii = program_de_studii.substring(0,space_index); //aici e programul obtinut corect
                        try {
                            if(secretariat.verifica_student_duplicat(nume_student) == 1)
                                throw new ExceptieStudentExistent("Student duplicat: " + nume_student);
                            else
                                secretariat.adauga_student(nume_student,program_de_studii);
                        } catch(ExceptieStudentExistent e) {
                            s.write("***");
                            s.newLine();
                            s.write(e.getMessage());
                            s.newLine();
                        }
                    } else if(comanda.equals("adauga_curs")) {
                        String program_de_studii = linie.substring(space_index + 3);
                        space_index = program_de_studii.indexOf(' ');
                        String nume_curs = program_de_studii.substring(space_index + 3);
                        program_de_studii = program_de_studii.substring(0,space_index);
                        int capacitate_maxima = Integer.parseInt(nume_curs.substring(nume_curs.indexOf(' ') + 3));
                        nume_curs = nume_curs.substring(0,nume_curs.indexOf(' '));
                        secretariat.adauga_curs(program_de_studii,nume_curs,capacitate_maxima);
                    } else if(comanda.equals("citeste_mediile")) {
                        String cale_director_cu_note =  "src/main/resources/" + args[0];
                        File director_note = new File(cale_director_cu_note);
                        File[] fisiere = director_note.listFiles();
                        for(int i = 0;i < fisiere.length;i++)
                            if(fisiere[i].getName().substring(0,5).equals("note_")) {
                                FileReader continut = new FileReader(cale_director_cu_note + "/" + fisiere[i].getName());
                                BufferedReader citeste_note = new BufferedReader(continut);
                                String linie_curenta;
                                do {
                                    linie_curenta = citeste_note.readLine();
                                    if(linie_curenta != null) {
                                        String nume_stud = linie_curenta.substring(0,linie_curenta.indexOf(' '));
                                        double nota = Double.parseDouble(linie_curenta.substring(linie_curenta.indexOf(' ') + 3));
                                        secretariat.asigneaza_nota(nume_stud,nota);
                                    }
                                } while(linie_curenta != null);
                            }

                    } else if(comanda.equals("posteaza_mediile")) {
                        Collections.sort(secretariat.getStudenti(), new ClasaDeComparare() {
                            @Override
                            public int compare(Student a, Student b) {
                                if((a.getMedie() < b.getMedie()) || (a.getMedie() == b.getMedie() && a.nume.compareTo(b.nume) > 0))
                                    return 1;
                                return -1;
                            }
                        });
                        secretariat.afiseaza_medii(f2,s);
                    } else if(comanda.equals("contestatie")) {
                        space_index = linie.indexOf(' ');
                        String nume_student_contestatie = linie.substring(space_index + 3);
                        space_index = nume_student_contestatie.indexOf(' ');
                        double medie_noua = Double.parseDouble(nume_student_contestatie.substring(space_index + 3));
                        nume_student_contestatie = nume_student_contestatie.substring(0,space_index);
                        secretariat.solutioneaza_contestatie(nume_student_contestatie,medie_noua);
                    } else if(comanda.equals("adauga_preferinte")) {
                        space_index = linie.indexOf(' ');
                        String preferinte = linie.substring(space_index + 3); //scap de adauga-preferinte -
                        space_index = preferinte.indexOf(' ');
                        String nume_student = preferinte.substring(0,space_index);
                        preferinte = preferinte.substring(space_index + 3);
                        Student q = null;
                        for(int k = 0;k < secretariat.getStudenti().size();k++)
                            if(secretariat.getStudenti().get(k).nume.equals(nume_student)) {
                                q = secretariat.getStudenti().get(k);
                                break;
                            }
                        String preferinta_curenta;
                        int line_index = preferinte.indexOf('-');
                        while(line_index != -1) {
                            preferinta_curenta = preferinte.substring(0,line_index - 1);
                            secretariat.adauga_preferinta(q,preferinta_curenta);
                            preferinte = preferinte.substring(line_index + 2,preferinte.length());
                            line_index = preferinte.indexOf('-');
                        }
                        preferinta_curenta = preferinte;
                        secretariat.adauga_preferinta(q,preferinta_curenta);
                    } else if(comanda.equals("repartizeaza")) {
                        secretariat.repartizeaza();
                    } else if(comanda.equals("posteaza_curs")) {
                        String nume_curs;
                        int line_index = linie.indexOf('-');
                        nume_curs = linie.substring(line_index + 2);
                        secretariat.posteaza_curs(nume_curs,f2,s);
                    } else if(comanda.equals("posteaza_student")) {
                        String nume_student;
                        int line_index = linie.indexOf('-');
                        nume_student = linie.substring(line_index + 2);
                        secretariat.posteaza_student(nume_student,f2,s);
                    }
                }
            } while (linie != null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException t) {
            t.printStackTrace();
        } finally {

            try {
                if (r != null)
                    r.close();
                if (s != null)
                    s.close();
                if(f != null)
                    f.close();
                if(f2 != null)
                    f2.close();

            } catch(IOException q) {
                q.printStackTrace();
            }
        }
    }

}




