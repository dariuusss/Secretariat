	In cadrul acestui proiect am efectuat o simulare a procesului
de repartizare a studentilor la optionale.Pentru aceasta am utilizat clasele:
ClasaDeComparare,Curs,ExceptieStudentExistent,Main,Secretariat,Student,
StudentLicenta si StudentMaster,cu urmatoarele functionalitati:

	- ClasaDeComparare: clasa abstracta care implementeaza interfata Comparator 
care accepta obiecte de tip Student;are rol in sortarea studentilor 
in functie de medie si nume
	- Curs: implementeaza un curs de la facultate; 
are drept campuri denumirea,capacitatea,numarul de elevi inscrisi,
media minima de admitere, ciclul de studii(licenta sau master)
si lista de studenti inrolati la curs
	- ExceptieStudentExistent: clasa copil a clasei Exception;
este utilizata pentru gestionarea exceptiei pentru cazul in care se 
incearca inrolarea a doi studenti cu acelasi nume
	- Main: contine metoda main,care interpreteaza 
argumentele din linia de comanda si apeleaza 
metodele corespunzatoare ale clasei Secretariat
	- Secretariat: este entitatea principala a proiectului,contine 
metodele necesare pentru adaugarea studentilor,adaugarea cursurilor,asignarea notelor,
adaugarea de preferinte,solutionarea de contestatii,postarea cursurilor,
studentilor si repartizarea 
	- Student: simuleaza entitatea de student,are drept campuri:
numele,media si lista de preferinte.


	In cadrul rezolvarii temei am ales sa folosesc sa folosesc ArrayList din colectiile
Java datorita facilitatii aduse la operatiile de inserare,stergere si sortare cu ajutorul unor 
functii definite de utilizator.