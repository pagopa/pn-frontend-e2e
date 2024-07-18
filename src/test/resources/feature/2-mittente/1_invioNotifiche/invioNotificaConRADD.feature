Feature: Mittente invia una notifica analogica con controllo RADD

  @Parallel
  @annullamentoNotificaMittenteInvioInCorso
  @PA

  Scenario: [TA-FE INVIO DI UNA NOTIFICA E CHECK RADD] - Mittente invia una notifica analogica e verifica alert RADD
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Logout da portale mittente