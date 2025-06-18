Feature: Avviso PagoPa - inserire n avvisi PagoPA e m modelli F24 per uno stesso destinatario - con n diverso da m

  @TestSuite
  @TA_PosizioneDebitoria_17_19_20_37
  @TA_PosizioneDebitoria_ON
  @NRT_Blocco_1
  Scenario: [Posizione_Debitoria_17_19_20_37] - Avviso PagoPa - inserire n avvisi PagoPA e m modelli F24 per uno stesso destinatario PF - con n diverso da m
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "Posizione_Debitoria_17_19_20_37"
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
##    Posizione Debitoria
    When Seleziona Avviso PagoPA add Modello F24 1
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2
    And Click Su Elimina Avviso pagoPA
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 3
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
    And Click Su Aggiungi Altro Modello F24 0
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 2
    And Inserisci Titolo Documento Posizione Debitoria 2
    And Click Su Elimina Modello F24
    And Click Su Aggiungi Altro Modello F24 0
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 2
    And Inserisci Titolo Documento Posizione Debitoria 2
    And Verifica Numero Caricamento file 5
    And Cliccare su continua
 ## Documenti allegati
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia Posizione Debitoria
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Aspetta 1 secondi
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Attendi secondi "2"
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Sezione Pagamenti

    Then PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Aspetta 1 secondi
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Attendi secondi "2"
    And Cliccare sul bottone Filtra Notifica "filter-notifications-button"
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Sezione Pagamenti


  
