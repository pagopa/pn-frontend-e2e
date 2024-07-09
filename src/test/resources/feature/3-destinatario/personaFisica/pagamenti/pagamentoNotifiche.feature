Feature:Pagamento notifica

  @TestSuite
  @PF
  @PagamentoNotificaPF

  Scenario:[NOTIFICA-PAGAMENTO NOTIFICA AVVISO PAGOPA] Verifica testo rimborso su notifica pagata e successivamente annullata
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
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Si seleziona la notifica
