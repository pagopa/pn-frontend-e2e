Feature: annullamento della notifica

  @annullamentoNotificaPF
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica con avviso PagoPa e F24, la annulla e controlla quali file sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
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
      | provincia         | Mi                 |
      | stato             | Italia             |
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
      | costiNotifica     | true               |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome       | Lucrezia Borgia  |
      | codiceFiscale     | BRGLRZ80D58H501Q |
      | tipoDestinatario  | PF               |
    Then Creo in background una notifica per destinatario tramite API REST
    And Logout da portale mittente