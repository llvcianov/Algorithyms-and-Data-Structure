//L'algoritmo è una versione modificata della ricerca binaria. Prende in input un array unimodale di lunghezza definita dall'utente.
//Sull'array viene chiamata la funzione Ricerca(). Tale funzione divide l'array in due sottoarray e chiama ricorsivamente se stessa su uno dei due.
//Per decidere il sottoarray giusto viene eseguito un confronto tra l'elemento medio dell'array e il suo successore

//La complessità è la stessa della ricerca binaria: O(log n). 


#include <iostream>
using namespace std;

int massimo = 0;

void Ricerca (int A[], int in, int f){

    if (in == f){       //se gli indici coincidono ho trovato il massimo
        massimo = A[in];
        return;
    }
        
    int medio = in + (f - in) / 2;      //calcola indice medio array

    if(A[medio] > A[medio+1]) 
        f = medio;      //cerca nel sottoarray sx
    else
        in = medio+1;   //cerca nel sottoarray dx

    Ricerca(A,in,f);    //chiama ricorsivamente 
 
}

int main() {

    int N = 0;
   
    cout << "Inserisci numero ed elementi array" << endl;
    cin >> N;
    int* A = new int[N];
    for(int i = 0; i < N; i++){
        cin >> A[i];
    }

    int in = 0;         //indice inizio array
    int f = N - 1;      //indice fine array
    Ricerca(A,in,f);

    cout<<"Il massimo del vettore e' "<< massimo << endl;

    delete [] A;

    return 0;
}


