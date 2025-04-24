Feature: Avviso PagoPa - inviaare una notifica multi destinatario a PF e PG contenente più avvisi PagoPa e più modelli F24

  @TestSuite
  @TA_PosizioneDebitoria_39
  @NRT_TA_PosizioneDebitoria

  Scenario: [Posizione_Debitoria_39] - Avviso PagoPa - inviaare una notifica multi destinatario a PF e PG contenente più avvisi PagoPa e più modelli F24
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0

    And Nella section Destinatario cliccare su Aggiungi destinatario
## Persona Giuridica
    And Nella section Destinatario selezionare il radio button persona giuridica posizionale 1
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica posizionale 1
#    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica" destinatario 1

    And Cliccare su continua
##    Posizione Debitoria
    When Seleziona Avviso PagoPA add Modello F24 1
    When Seleziona Avviso PagoPA add Modello F24 2
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Sincrono 1
#Persona Fisica
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2

    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 1
    And Inserisci Titolo Documento Posizione Debitoria 1

    And Click Su Aggiungi Altro Modello F24 0
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 2
    And Inserisci Titolo Documento Posizione Debitoria 2

#Persona Giuridica
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 3
    And Click Su Aggiungi Codice Di Avviso PagoPa 1
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 4
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 3
    And Inserisci Titolo Documento Posizione Debitoria 3

    And Click Su Aggiungi Altro Modello F24 1
    And Carica Json senza Costi Posizione Debitoria Numero Notifiche Pari a 4
    And Inserisci Titolo Documento Posizione Debitoria 4


    And Cliccare su continua
 ## Documenti allegati
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Attendi secondi "2"
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"

    And Aspetta 1 secondi
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra
##    And Si verifica che la notifica sia nello stato avanzato

    And Cliccare sulla notifica restituita dal filtro
    And Verifica Presenza Sezione Pagamenti nel menu a cascata 2


    When PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Aspetta 1 secondi
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra persona fisica
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Presenza Codici Avviso PagoPa 2 e ModelloF24 2
#    And Verifica Codici Avvisi

    Then PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Aspetta 1 secondi
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra persona giuridica
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Presenza Codici Avviso PagoPa 2 e ModelloF24 2