Feature: Avviso PagoPa - inserire più modelli F24 per uno stesso destinatario

  @TestSuite
    @TA_PosizioneDebitoria_13_15
  @NRT_TA_PosizioneDebitoria

  Scenario: [Posizione_Debitoria_13_15] - Avviso PagoPa - inserire più modelli F24 per uno stesso destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0

    And Cliccare su continua
#    Posizione Debitoria
    When Seleziona Modello F24 1
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1

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

    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra
##    And Si verifica che la notifica sia nello stato avanzato
    And Cliccare sulla notifica restituita

    And Verifica Presenza Sezione Pagamenti numero moduli F24 2
