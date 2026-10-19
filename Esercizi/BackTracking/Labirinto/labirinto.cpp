# include <iostream>
#include <vector>

using namespace std;
#define N 5

//N.B Nel codice x rappresenta indice di riga e y indice di colonna

bool costrusci_candidati(const vector<vector<int>>& L, int x, int y, vector<vector<int>>& scelte){
    int counter = 0;
    if (y + 1 < L.size() && L[x][y + 1] == 1) {  // Verifica se y+1 è dentro i limiti
        scelte.push_back({x, y + 1}); // Aggiungi la coppia (x, y+1) a scelte
        counter++;
    }
    if (x + 1 < L.size() && L[x + 1][y] == 1) {  // Verifica se x+1 è dentro i limiti
        scelte.push_back({x + 1, y}); // Aggiungi la coppia (x, y+1) a scelte
        counter++;
    }
    if(counter == 0)
        return false;
    else
        return true;
}

bool is_a_solution(int x, int y){
    if (x == N-1 && y == N-1)
        return true;
    else
        return false;
}

void stampa_percorso(const vector<vector<int>>& S) {

    // Crea una matrice di zeri di dimensione N
    vector<vector<int>> matrice(N, vector<int>(N, 0));
    matrice[0][0] = 1;

    // Imposta a 1 le posizioni specificate in S
    for (const auto& pos : S) {
        int x = pos[0];
        int y = pos[1];
        matrice[x][y] = 1;
    }

    // Stampa la matrice risultante
    for (const auto& row : matrice) {
        for (int value : row) {
            cout << value << " ";
        }
        cout << endl;
    }
     cout << endl;
}

void CercaPercorso(vector<vector<int>>& S, const vector<vector<int>>& L, int x, int y){

    vector<vector<int>> scelte; //matrice formata dalle coppie di candidati

    if (is_a_solution(x, y)){   //verifica se la posizione corrente è la fine del labirinto, se si stampa il percorso
        stampa_percorso(S);
    } else {
        if (costrusci_candidati(L, x, y, scelte)){

            //itero sul vettore delle possibili scelte
            for (const auto& riga : scelte) {
                //mi salvo la posizione corrente per ripristinarla nel caso abbia fatto una scelta sbagliata
                int x_orig = x;
                int y_orig = y;
                //aggiungo la scelta fatta al vettore delle soluzioni
                S.push_back({riga[0],riga[1]});  
                //eseguo la mossa
                x = riga[0];
                y = riga[1];
                CercaPercorso(S, L, x, y);
                //se sono qui è perchè ho compiuto una scelta sbagliata, tolgo la mossa effettuata dal vett. delle soluzioni
                S.pop_back();
                //ripristino la posizione e torno al chiamante
                x = x_orig;
                y = y_orig;
            }
        }       
    }      
}

int main(){
    
    /*
    cout << "Inserisci la dimensione della matrice:";
    cin >> N;

    int* labirinto = new int [N][N];
    */

    vector<vector<int>> labirinto = {
        {1, 1, 1, 0, 1},
        {1, 0, 0, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1},
        //{1, 0, 0, 1}
    };

    for (const auto& row : labirinto) {
        for (int value : row) {
            cout << value << " ";
        }
        cout << endl;
    }
    cout << endl << endl;

    vector<vector<int>> S;

    int x = 0;  //posizione di partenza x
    int y = 0;  //posizione di partenza y
    int k = 0;

    CercaPercorso(S, labirinto, x, y);


    return 0;
}