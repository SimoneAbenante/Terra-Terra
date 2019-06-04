# TERRA~TERRA RISTORANTE

#### Applicazione web con architettura a microservizi, per la gestione di un ristorante.

Sviluppata per finalità didattiche sulle seguenti **tecnologie:**

- **Spring** (MVC, Boot, Data, ecc.)
- **AngularJS** (menu di selezione della vista: gestore, cameriere, cucina, coda comande, statistiche)
- **MySQL**
- **Pubnub** per l'aggiornamento real-time dei contenuti

### Cameriere
Gli addetti devono avere la possibilità di:

- visualizzare lo stato dei tavoli;
- visualizzare la lista dei piatti da servire;
- cambiare lo stato di un piatto da “pronto” a “servito”;
- inserire le comande (jobs) dei vari tavoli (dining_table);
- produrre il conto (bill).

Non sono previste varianti alle pietanze (dishes) del menu; ogni pietanza avrà un prezzo associato.
Un ordine sarà composto da diverse comande (jobs) = pietanza + quantità, destinate alla Cucina.
### Gestore
L’interfaccia di amministrazione deve permettere di:
- Aggiungere nuovi piatti e stabilire il loro prezzo;
- Produrre il conto (bill) e accettare il pagamento di un conto (prodotto da lui o da un Cameriere);
- Visualizzare l’incasso totale.

### Cucina
L’interfaccia per la cucina deve consentire di:
- Visualizzare le comande da lavorare;
- Prendere in carico una comanda, cambiandone lo stato;
- Dichiarare “pronta” (ready) una comanda.

## Suggerimenti
Concentrarsi PRIMA sulle funzionalità, e POI sull’aspetto grafico (che potrà essere migliorato in seguito).
Progettare accuratamente i riferimenti tra le varie Entità coinvolte.
Utilizzare SEMPRE la lingua inglese per denominare le Entità, gli URL, ecc.
Utilizzare il singolare sia per i nomi delle Entità che per le tabelle associate.
