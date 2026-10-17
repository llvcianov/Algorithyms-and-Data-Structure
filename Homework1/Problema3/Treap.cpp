//L'algoritmo prende in ingresso un numero N di nodi scelto dall'utente. Ogni nodo ha una chiave scelta in fase di inserimento e una priorità generata casualmente
//Ogni nodo viene inserito con un normale Tree_Insert() in base alla chiave. Successivamente alla sua ggiunta sull'albero viene chiamata ricorsivamente
//su di esso la funzione bilancia(). Tale funzione esegue delle rotazioni a sx/dx che rendono l'albero un min-heap in base alla priorità, mantenendo 
//la struttura di un albero di ricerca binario in base alla chiavi.

// RotazioneSx() e Rotazione Dx() hanno complessità O(1), in quanto solo i puntatori vengono modificati da una rotazione
//La Tree Insert ha invece una complessità O(h) quindi nel caso peggiore è O(log n).
//Di conseguenza la complessità dell'algoritmo è O(n log n), poichè chiamiamo n volte la Treap Insert()



#include <iostream>
#include <string>

using namespace std;

class Nodo {
public:
    char chiave;
    int priorità; 
    Nodo* figlioSx;
    Nodo* figlioDx;
    Nodo* padre; 

    Nodo(char NuovaChiave, int NuovaPriorità) : chiave(NuovaChiave), priorità(NuovaPriorità), figlioSx(nullptr), figlioDx(nullptr), padre(nullptr) {}
};

class Treap {
public:
    Nodo* radice;

    Treap() : radice(nullptr) {}

    void rotazioneDx(Nodo* x) {

        Nodo* y = (x->figlioSx);    //variabile di appoggio per il figlio sinistro

        (x->figlioSx) = (y->figlioDx);     //"divide" i nodi
        if((y->figlioDx) != (nullptr))
            ((y->figlioDx)->padre) = x;      
            
        (y->padre) = (x->padre);        //assegnazione padre

        if((x->padre) == nullptr)       //check se sono arrivato in cima, altrimenti aggiorno i figli del padre
            radice = y;

        else if ((x == ((x->padre)->figlioDx)))
            ((x->padre)->figlioDx) = y;
        else 
            ((x->padre)->figlioSx) = y;

        (y->figlioDx) = x;      //scambio relazione tra x e il suo figlioDx
        (x->padre) = y;
    }

    void rotazioneSx(Nodo* x) {

        Nodo* y = (x->figlioDx);    //variabile di appoggio per il figlio destro

        (x->figlioDx) = (y->figlioSx);     //"divide" i nodi
        if((y->figlioSx) != (nullptr)) 
            ((y->figlioSx)->padre) = x;      
            
        (y->padre) = (x->padre);        //assegnazione padre

        if((x->padre) == nullptr)      //check se sono arrivato in cima, altrimenti aggiorno i figli del padre
            radice = y;

        else if ((x == ((x->padre)->figlioSx)))
            ((x->padre)->figlioSx) = y;
        else 
            ((x->padre)->figlioDx) = y;
        

        (y->figlioSx) = x;      //scambio relazione tra x e il suo figlioSx
        (x->padre) = y;
    }

    void bilancia(Nodo* x){
        
        if((x->padre) != nullptr){    

            if((x->priorità) < ((x->padre)->priorità)){

                if (x == (x->padre->figlioDx))  //se  il nodo è un figlio destro eseguo rotazione a sinistra
                    rotazioneSx(x->padre);
                else
                    rotazioneDx(x->padre);  //viceversa

               bilancia(x); //richiamo ricorsivamente la funzione sul nodo per farlo salire nell'albero
            } 
        }   
    }
 
    void Treap_insert(char NuovaChiave, int NuovaPriorità) {

        Nodo* nuovoNodo = new Nodo(NuovaChiave, NuovaPriorità);
        Nodo* y = nullptr;     // Puntatore al padre del nodo analizzato
        Nodo* x = radice;      // Puntatore al nodo analizzato 

        while (x != nullptr) {  // Entra se l'albero non è vuoto, quindi al 2 nodo inserito
            y = x; 

            if (NuovaChiave < (x->chiave)) 
                x = (x->figlioSx); // Scende a sinistra
            else 
                x = (x->figlioDx); // Scende a destra
        }

        if (y == nullptr) { // Albero vuoto, entra solo all'inserimento del primo nodo che diventa la radice
            radice = nuovoNodo; 

        } else if (NuovaChiave < (y->chiave)) { 
            (y->figlioSx) = nuovoNodo; // Inserisce a sinistra
            (nuovoNodo->padre) = y; 

        } else {
            (y->figlioDx) = nuovoNodo; // Inserisce a destra
            (nuovoNodo->padre) = y; 
        }

        bilancia(nuovoNodo);    //dopo l'inseirmento di ogni nodo bilanciamo l'albero in base alle priorità
    }

    // Funzione di stampa del treap in forma grafica
    void printTreapHelper(Nodo* root, string prefix, bool isLeft) {
        if (root != nullptr) {
            cout << prefix;

            // Mostra l'indicatore per il nodo corrente
            cout << (isLeft ? "├──" : "└──");

            // Stampa il valore del nodo
            cout << root->chiave << ":" << root->priorità << endl;

            // Passa ai figli sinistro e destro, aggiungendo spaziatura
            // Stampa l'arco per il figlio destro, anche se mancante
            if (root->figlioDx != nullptr) {
                printTreapHelper(root->figlioDx, prefix + (isLeft ? "│   " : "    "), true);
            } else {
                 cout << prefix + (isLeft ? "│   ├──" : "    ├──") << "-" << endl;
            }

            // Stampa l'arco per il figlio sinistro, anche se mancante
            if (root->figlioSx != nullptr) {
                printTreapHelper(root->figlioSx, prefix + (isLeft ? "│   " : "    "), false);
            } else {
                cout << prefix + (isLeft ? "│   └──" : "    └──") << "-" << endl;
            }
        }
    }

};

int main() {
    Treap albero;
    int N;
    char chiave;
    int priorità;

    srand(time(0));

    cout << "Quanti elementi vuoi inserire nel Treap? ";
    cin >> N;

    cout << "Inserisci le chiavi" << endl;
    
    for (int i = 0; i < N; i++) {
        cout << "Carattere " << (i + 1) << ": ";
        cin >> chiave;
        int priorità = 1 + (rand() % 100);
        albero.Treap_insert(chiave, priorità);
        
    }
    albero.printTreapHelper(albero.radice, "", false);

    return 0;
}
