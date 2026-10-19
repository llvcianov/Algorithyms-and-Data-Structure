# Prodotti

> Non è stato ritrovato il testo originale della consegna — descrizione ricostruita dal codice.

Dato un budget e N classi di prodotti (ciascuna con una propria lista di costi), si vuole scegliere **al più un prodotto per classe** (eventualmente nessuno) in modo da massimizzare la spesa totale complessiva senza superare il budget disponibile.

## Input

Il budget totale e il numero di classi N; per ciascuna delle N classi, il numero di prodotti in quella classe seguito dai loro costi.

## Approccio

Programmazione dinamica top-down con memoization su (classe, budget residuo): per la classe corrente si prova ogni prodotto il cui costo rientra nel budget residuo, scegliendo quello che massimizza la spesa totale sommata al risultato ricorsivo sulla classe successiva con budget ridotto. Il caso base è raggiunto quando le classi sono esaurite o il budget residuo è nullo.
