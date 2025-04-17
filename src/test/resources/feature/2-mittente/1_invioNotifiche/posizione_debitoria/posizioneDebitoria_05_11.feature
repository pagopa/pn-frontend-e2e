Feature: Avviso PagoPa - inserire un codice avviso per ogni destinatario a cui è indirizzata la notifica

  @TestSuite
  @TA_PosizioneDebitoria_05_11
  @NRT

  Scenario Outline: [Posizione_Debitoria_05_11] - Avviso PagoPa - inserire un codice avviso per ogni destinatario a cui è indirizzata la notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati delle persone fisiche aggiuntive per <numero destinatari>

    And Cliccare su continua
#    Posizione Devitoria
    When Seleziona Avviso PagoPA 1
    When Seleziona Avviso PagoPA 2
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    When Seleziona Incluso Nell Atto 1
    And Seleziona Modo Asincrono 1
##  Posizione debitoria di xxxx
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Multiplo File PDF Posizione Debitoria Numero Notifiche Pari a 2
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
    And Verifica Presenza Sezione Pagamenti nel menu a cascata 2

    Examples:
      | numero destinatari |
      |2                   |