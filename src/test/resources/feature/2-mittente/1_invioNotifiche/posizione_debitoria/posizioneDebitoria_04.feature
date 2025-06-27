Feature: Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona

  @TestSuite
  @TA_PosizioneDebitoria_04
  @TA_PosizioneDebitoria_ON
  @NRT_Blocco_1
  Scenario: [Posizione_Debitoria_04] - Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "Posizione_Debitoria_04"
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
#    Posizione Debitoria 04
    When Seleziona Avviso PagoPA 1
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
##  Posizione debitoria di xxxxx
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
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
    #And Aspetta 1 secondi
    And Refresh pagina
    And Verifica Sezione Pagamenti
    
