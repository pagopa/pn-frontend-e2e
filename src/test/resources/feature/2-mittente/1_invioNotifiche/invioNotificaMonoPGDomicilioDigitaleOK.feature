Feature: Mittente genera una notifica che non prevede pagamento

  @paralell
  @TA_InvioNotificaMonoPGDomicilioDigitaleOK
  @mittente
  @invioNotifiche

  Scenario: PN-9291 - Mittente genera una notifica mono destinatario a PG con domicilio digitale KO
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         |via @FAIL-Irreperibile_AR      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Convivio Spa      |
      | codiceFiscale     | 27957814470       |
      | tipoDestinatario  | PG                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
    And Logout da portale mittente