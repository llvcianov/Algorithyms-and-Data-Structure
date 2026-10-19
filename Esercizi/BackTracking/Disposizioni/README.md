# Disposizioni

Genera ed stampa tutte le disposizioni (permutazioni) di un insieme di N valori interi letti da input, usando il backtracking.

## Input

Da standard input: il numero N di valori, seguito dagli N valori stessi (interi da 1 a N). Il programma stampa una riga per ciascuna delle N! disposizioni possibili.

## Approccio

Segue lo schema generico di backtracking illustrato da Steven Skiena nella sua lezione "Backtracking" (sezione "Constructing Permutations by Backtracking", dal suo libro *The Algorithm Design Manual*): una soluzione parziale `S` viene estesa un elemento alla volta, scegliendo ad ogni passo tra i candidati non ancora usati (`costruisci`), finché `S` raggiunge la dimensione dell'input (`backtrack`); ad ogni soluzione completa viene stampata (`process_solution`).
