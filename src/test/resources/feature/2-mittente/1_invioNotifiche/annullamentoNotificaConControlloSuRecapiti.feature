Feature: Mittente invia una notifica analogica o digitale che viene annullata e che segue controlli recapiti


  @TA_annullamentoNotificaMittenteEControlloDownloadPEC
  @Parallelism
  @PA
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica a destinatario con PEC impostata e la annulla, si controlla che le ricevute PEC sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         | VIA ROMA 20      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Gaio Giulio      |
      | codiceFiscale     | CSRGGL44L13H501E |
      | tipoDestinatario  | PF               |
      | domicilioDigitale | test@test.com    |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Aspetta 60 secondi
    And Si attende completamento notifica "Consegnata"
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 10 secondi
    And Si controlla che le ricevute PEC siano scaricabili
    And Logout da portale mittente


  @TA_annullamentoNotificaMittenteEControlloPEC
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica a destinatario con PEC impostata e la annulla, si controlla che le ricevute PEC sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         | VIA ROMA 20      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Gaio Giulio      |
      | codiceFiscale     | CSRGGL44L13H501E |
      | tipoDestinatario  | PF               |
      | domicilioDigitale | test@test.com    |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Aspetta 60 secondi
    And Si attende completamento notifica "Invio in corso"
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 10 secondi
    And Si controlla che le ricevute PEC siano scaricabili
    And Logout da portale mittente

  @TA_annullamentoNotificaMittenteEControlloMailDiCortesia
  @recapitiPF
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica a destinatario con PEC impostata e la annulla, si controlla che le ricevute PEC sono scaricabili
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una "email di cortesia" e si inserisce "prova@test.it"
    And Logout da portale persona fisica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         | VIA ROMA 20      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Gaio Giulio      |
      | codiceFiscale     | CSRGGL44L13H501E |
      | tipoDestinatario  | PF               |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Aspetta 60 secondi
    And Si attende completamento notifica "Invio in corso"
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 10 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"È in corso l'invio del messaggio di cortesia tramite email.")] |
      | vediDettagli | true  |
    And Logout da portale mittente


