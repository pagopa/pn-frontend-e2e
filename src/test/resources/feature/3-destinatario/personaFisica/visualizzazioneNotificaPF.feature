Feature:Controllo dati notifica

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  @CheckNotificaConDocumentiAllegatiPF1
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PF visualizza Notifica mono destinatario non ancora pagata solo con avviso PagoPa e assenza del PDF relativo al bollettino
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "YRUZ-NYXJ-DAJK-202405-N-1"
    And Cliccare sul bottone Filtra del delegato
    And Si clicca la notifica ricercata
    And Si controlla la presenza di codice avviso
    And Si controlla non sia presente l'avviso PagoPa
    And Logout da portale persona fisica

  Scenario: [TA-FE INVIO DI UNA NOTIFICA A PERSONA FISICA E ANNULLAMENTO] - Mittente invia una notifica e la annulla, anche la persona fisica vede la notifica annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso             |
      | indirizzo         | VIA ROMA 20        |
      | dettagliIndirizzo | Scala b            |
      | codicePostale     | 20147              |
      | comune            | Milano             |
      | dettagliComune    | Milano             |
      | provincia         | MI                 |
      | stato             | Italia             |
      | nomeCognome       | Gaio Giulio       |
      | codiceFiscale     | CSRGGL44L13H501E        |
      | tipoDestinatario  | PF                |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 0                 |
      | F24               | 2                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica destinatario
    And Si attende completamento notifica "Consegnata"
    And Si clicca sul modello F24 destinatario numero 1
    And Si clicca sul modello F24 destinatario numero 2