Feature: Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona

  @TestSuite
  @TA_PosizioneDebitoria_23_27_31_32_24_30_28_33_26_25_34_29_35
  @TA_PosizioneDebitoria_ON
  @NRT_Blocco_1
  Scenario: [Posizione_Debitoria_23_27_31_32_24_30_28_33_26_25_34_29_35] - Verificare che, a fronte di un cambio tipologia di pagamento tra la prima e la seconda volta, i campi corrispondenti al pagamento vengano correttamente svuotati
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "Posizione_Debitoria_23_27_31_32_24_30_28_33_26_25_34_29_35"
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
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
    And Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Cliccare su continua
## Posizione Debitoria 24
    And Cliccare su Torna a
    And Cliccare su Torna a
    And Verifica Avviso PagoPA 1
    And Seleziona Avviso PagoPA add Modello F24 1
    And Cliccare su continua
    And Verifica Presenza Sezione Tecnologia Pagamento Avviso PagoPA
    And Verifica Presenza Sezione Specifiche Avviso PagoPA 1
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
## Posizione Debitoria 30
    And Cliccare su Torna a
    And Verifica Avviso PagoPA add Modello F24 1
    And Seleziona Modello F24 1
    And Cliccare su continua
    And Verifica Assenza Sezione Tecnologia Pagamento Avviso PagoPA
    And Verifica Assenza Sezione Specifiche Avviso PagoPA 1
    And Verifica Presenza Sezione Specifiche Modello F24 1
    And Cliccare su continua
## Posizione Debitoria 28
    And Cliccare su Torna a
    And Cliccare su Torna a
    And Verifica Modello F24 1
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
## Posizione Debitoria 33
    And Cliccare su Torna a
    And Verifica Nessun Pagamento 1
    And Seleziona Modello F24 1
    And Cliccare su continua
    And Seleziona Incluso Nell Atto 1
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
## Posizione Debitoria 26
    And Cliccare su Torna a
    And Verifica Modello F24 1
    And Seleziona Avviso PagoPA 1
    And Cliccare su continua
    And Verifica Assenza Sezione Specifiche Modello F24 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Cliccare su continua
## Posizione Debitoria 25
    And Cliccare su Torna a
    And Cliccare su Torna a
    And Verifica Avviso PagoPA 1
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
## Posizione Debitoria 34
    And Cliccare su Torna a
    And Verifica Nessun Pagamento 1
    And Seleziona Avviso PagoPA add Modello F24 1
    And Cliccare su continua
    And Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1
    And Cliccare su continua
## Posizione Debitoria 29
    And Cliccare su Torna a
    And Cliccare su Torna a
    And Verifica Avviso PagoPA add Modello F24 1
    And Seleziona Avviso PagoPA 1
    And Cliccare su continua
    And Verifica Presenza Sezione Tecnologia Pagamento Avviso PagoPA
    And Verifica Presenza Sezione Specifiche Avviso PagoPA 1
    And Verifica Assenza Sezione Specifiche Modello F24 1
    And Cliccare su continua
## Posizione Debitoria 35
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia
    And Attendi secondi "2"
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Cliccare sulla notifica restituita dal filtro
    And Refresh pagina
    And Verifica Sezione Pagamenti