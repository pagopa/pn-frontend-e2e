Feature: Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona

  @TestSuite
  @TA_PosizioneDebitoria_23_27_31_32
  @NRT

  Scenario: [Posizione_Debitoria_23_27_31_32] - Verificare che, a fronte di un cambio tipologia di pagamento tra la prima e la seconda volta, i campi corrispondenti al pagamento vengano correttamente svuotati
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
## Dettaglio posizione debitoria Avviso PagoPa
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
## Torna a Posizione debitoria -> Cambio a Modello F24
    And Cliccare su Torna a
    And Verifica Avviso PagoPA 1
    And Seleziona Modello F24 1
    And Cliccare su continua
## Verifica assenza sezioni Pagamento PagoPA
    And Verifica Assenza Sezione Tecnologia Pagamento Avviso PagoPA
    And Verifica Assenza Sezione Specifiche Avviso PagoPA 1
## Posizione Debitoria 27
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
    And Cliccare su Torna a
    And Verifica Modello F24 1
    And Seleziona Avviso PagoPA add Modello F24 1
    And Cliccare su continua
## Dettaglio posizione debitoria Avviso PagoPa + Modello F24
    And Verifica Presenza Sezione Specifiche Modello F24 1
    And Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
## Posizione Debitoria 31
    And Cliccare su Torna a
    And Verifica Avviso PagoPA add Modello F24 1
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
## Posizione Debitoria 32
    And Cliccare su Torna a
    And Verifica Nessun Pagamento 1
    And Seleziona Avviso PagoPA 1
    And Cliccare su continua
    And Verifica Presenza Sezione Tecnologia Pagamento Avviso PagoPA
    And Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Cliccare su continua
