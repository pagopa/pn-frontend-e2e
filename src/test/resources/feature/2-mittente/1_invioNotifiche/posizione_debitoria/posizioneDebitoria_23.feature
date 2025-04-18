Feature: Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona

  @TestSuite
  @TA_PosizioneDebitoria_23
  @NRT

  Scenario: [Posizione_Debitoria_23] - Verificare che, a fronte di un cambio tipologia di pagamento tra la prima e la seconda volta, i campi corrispondenti al pagamento vengano correttamente svuotati
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
#    Posizione Devitoria 04
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
    And Cliccare su Torna a
    And Cliccare su Torna a
    And Verifica Avviso PagoPA 1
    And Seleziona Modello F24 1
    And Cliccare su continua
    ## Step Dettaglio posizione debitoria
    And Verifica Incluso Nell Atto 1
    And Verifica Modo Sincrono 1
##  Posizione debitoria di xxxxx
    #And Verifica Tutti Codice Avviso vuoto
    And Verifica Tutti Codice Fiscale Ente
    
