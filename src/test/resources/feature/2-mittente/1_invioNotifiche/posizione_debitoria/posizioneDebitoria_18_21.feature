Feature: Avviso PagoPa - inserire un avviso PagoPA e un modello F24 per ogni destinatario a cui è indirizzata la notifica

  @TestSuite
  @TA_PosizioneDebitoria_18_21
  @TA_PosizioneDebitoria_ON
  @NRT
  Scenario Outline: [Posizione_Debitoria_18_21] - Avviso PagoPa - inserire un avviso PagoPA e un modello F24 per ogni destinatario a cui è indirizzata la notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati delle persone fisiche aggiuntive per <numero destinatari>
    And Cliccare su continua
#    Posizione Debitoria
    When Seleziona Avviso PagoPA add Modello F24 1
    And Seleziona Avviso PagoPA add Modello F24 2
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
# Posizione Debitoria
    And Carica Multiplo File 2 non pdf o json e visualizza messaggio di errore "json"
    And Si clicca sul bottone torna a informazioni preliminari
    And Cliccare su continua
    And Carica Multiplo File 2 non pdf o json e visualizza messaggio di errore "pdf"
    And Inserire Tutti Codice Avviso Errati
    And Inserire Tutti Codice Fiscale Ente Errati
    And Verifica Errori Codici Avviso NonValidi 2
    And Verifica Errori Codice Fiscale Ente NonValidi 2
    And Verifica Disabilitato Tasto Continua
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
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Sezione Pagamenti

    Examples:
      | numero destinatari |
      | 2                  |