package org.example;
import java.util.Comparator;

abstract class ClasaDeComparare implements Comparator<Student> {
    public int compara_studenti(Student a,Student b) {
        if((a.getMedie() < b.getMedie()) || (a.getMedie() == b.getMedie() && a.nume.compareTo(b.nume) > 0))
            return -1;
        return 1;
    }
}