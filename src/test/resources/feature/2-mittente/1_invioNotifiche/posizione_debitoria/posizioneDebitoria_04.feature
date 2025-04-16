Feature: Mittente genera una notifica tramite destinatario con pec

  @TestSuite
  @TA_PosizioneDebitoria_04
  @NRT

  Scenario: [Posizione_Debitoria_04] - Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
#    Posizione Devitoria 04
    And Seleziona Avviso PagoPA
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    And Seleziona Incluso Nell Atto
    And Seleziona Modo Sincrono
##  Posizione debitoria di Andrea De Franco
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Cliccare su continua
 ## Documenti allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra
#    And Si verifica che la notifica sia nello stato avanzato
    And Cliccare sulla notifica restituita
    And Verifica Presenza Sezione Pagamenti
    
