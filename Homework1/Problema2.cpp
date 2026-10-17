//L'algoritmo usa un approccio simile al merge sort. La funzione Dividi() divide un array di stringhe in due parti,
//e richiama ricorsivamente se stessa nei due sottoarray. Arrivato al passo base in cui si hanno solo due stringhe da confrontare 
//viene chiamata la funzione prefisso() che resituisce il prefisso comune più lungo tra le due.
//Oltre al passo base la funzione prefisso() viene chiamata quando una funzione Dividi() termina e il controllo viene passato al chiamante
//tale chiamata calcola il prefisso comune più lungo tra i risultati delle Dividi() precedenti.

//La complessità di questo algoritmo dipende dal numero di stringhe n e dal numero massimo di caratteri p di una stringa. Dovendo confrontare 
//carattere per carattere, nel caso peggiore, verranno effettuati np confronti. DI conseguenza, anche utilizzando un approccio divide et impera
//la complessità temporale resta O(np).

#include <iostream>
#include <string>
using namespace std;


string prefisso (string str1, string str2){     //confronta due stringhe e resituisce il prefisso in comune più lungo
    int i = 0;
    int strmin = 0;
    string out;

    if (str1.length() < str2.length()){         //controllo la stringa più corta
        strmin = str1.length();  

    } else {
        strmin = str2.length();
    }
    
    while (i < strmin && (str1[i] == str2[i])){     //copio in out finchè i caratteri sono uguali
        out += str1[i];
        i++;
    }

    return out;
}


void Dividi (string A[], int in, int f){

    if(f > in + 1){         //continuo a dividere

        int medio = in + (f - in) / 2;          //calcola indice medio array per dividere in sottoproblemi

        Dividi(A,in, medio);            //richiama ricorsivamente nel sottoarray a sx
        //punto 1

        Dividi(A,medio + 1,f);          //richiama ricorsivamente nel sottoarray a dx
        //punto2

        //qui devo devo effettuare il confronto tra i due prefissi comuni trovati nelle chiamate precedenti di Dividi
        //recupero i prefissi comuni e li confronto sovrascrivendo il primo prefisso
        //torno al chiamante nel punto 1 altrimenti se il chiamante è la prima Dividi chiamata torna nel main

        A[in] = prefisso (A[in], A[medio+1]);      

    } else {                 //Passo base con 2 stringhe

        //salvo il prefisso comune sovrascrivendo la 1° stringa analizzata
        //se sono nel passo base del sottoarray sx torno al chiamante nel punto 1
        //se sono nel passo base del sottoarray a dx torno al chiamante nel punto 2

        A[in] = prefisso(A[in],A[f]);       
       
    }
}


int main() {

    int N = 0;

    cout << "Inserisci numero e stringhe da analizzare" << endl;
    cin >> N;                       //input: numero di stringhe e stringhe
    string* A = new string[N];
    for(int i = 0; i < N; i++){
        cin >> A[i];
    }

    int in = 0;         //indice inizio array
    int f = N - 1;      //indice fine array
    Dividi(A,in,f);
    cout << "\nIl prefisso in comune piu' lungo e' " << A[in] << endl;      //alla fine delle chiamate l'output è in A[in]

    delete [] A;

    return 0;
}


