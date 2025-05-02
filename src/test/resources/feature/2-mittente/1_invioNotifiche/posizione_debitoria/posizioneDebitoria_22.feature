Feature: Avviso PagoPa - inserire un avviso PagoPA e un modello F24 per ogni destinatario a cui è indirizzata la notifica

  @TestSuite
  @TA_PosizioneDebitoria_22
  @TA_PosizioneDebitoria_ON
  @NRT
  Scenario Outline: [Posizione_Debitoria_22] - Avviso PagoPa - inserire un avviso PagoPA e un modello F24 per ogni destinatario a cui è indirizzata la notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati delle persone fisiche aggiuntive per <numero destinatari>
    And Cliccare su continua
#    Posizione Debitoria
    When Seleziona Avviso PagoPA 1
    And Seleziona Modello F24 2
  And Seleziona Avviso PagoPA add Modello F24 3
    And Seleziona Nessun Pagamento 4
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2
    And Inserisci Titolo Documento Posizione Debitoria 2
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 2
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Verifica Numero Caricamento file 4
    And Cliccare su continua
 ## Documenti allegati
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia Posizione Debitoria
    And Attendi secondi "2"
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 2 secondi
    And Verifica Sezione Pagamenti

    Examples:
      | numero destinatari |
      | 4                 |