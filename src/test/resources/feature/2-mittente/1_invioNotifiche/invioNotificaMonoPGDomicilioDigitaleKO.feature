Feature: Mittente genera una notifica che non prevede pagamento

  @TestSuite
  @TA_InvioNotificaMonoPGDomiccilioDigitaleKO
  @mittente
  @invioNotifiche

  Scenario: PN-9292 - Mittente genera una notifica mono destinatario a PG con domicilio digitale KO
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @FAIL-Irreperibile_AR |
      | codicePostale    | 20147                 |
      | comune           | Milano                |
      | dettagliComune   | Milano                |
      | provincia        | MI                    |
      | stato            | Italia                |
      | nomeCognome      | Test SPA              |
      | codiceFiscale    | 00749900049           |
      | tipoDestinatario | PG                    |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 360 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente