Feature: Avviso PagoPa - inserire n avvisi PagoPA e m modelli F24 per uno stesso destinatario - con n diverso da m

  @TestSuite
  @TA_PosizioneDebitoria_38
  @NRT_TA_PosizioneDebitoria

  Scenario: [Posizione_Debitoria_38] - Avviso PagoPa - inserire n avvisi PagoPA e m modelli F24 per uno stesso destinatario PG - con n diverso da m
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
## Persona Giuridica
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica
#    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica" destinatario 0

#    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
#    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0

    And Cliccare su continua
##    Posizione Debitoria
    When Seleziona Avviso PagoPA add Modello F24 1
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1

    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Click Su Aggiungi Codice Di Avviso PagoPa
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2

    And Click Su Aggiungi Codice Di Avviso PagoPa

    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 3


    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1

    And Click Su Aggiungi Altro Modello F24
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 2
    And Inserisci Titolo Documento Posizione Debitoria 2


    And Cliccare su continua
 ## Documenti allegati
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"

    And Aspetta 5 secondi
    
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra
##    And Si verifica che la notifica sia nello stato avanzato
    And Cliccare sulla notifica restituita
##    Numero Avviso PagoPa n + 1 perche viene incluso anche la dicitura Modelli F24 allegati se si scelie l'opzione Avviso PagoPA + Modello F24
    And Verifica Presenza Sezione Pagamenti 4
##    Numero moduli  Moduli F24
    And Verifica Presenza Sezione Pagamenti numero moduli F24 2

    Then PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare la notifica destinatario
    And Verifica Presenza Codici Avviso PagoPa 3 e ModelloF24 2
    And Verifica Codici Avvisi


  
