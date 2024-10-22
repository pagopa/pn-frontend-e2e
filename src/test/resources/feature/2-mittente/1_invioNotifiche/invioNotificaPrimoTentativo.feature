Feature: Mittente genera una notifica che non prevede pagamento

  @Parallel
  @TA_InviaNotificaConDestinatarioReperibileAlPrimoInvio
  Scenario: [TA-FE MITTENTE CREA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica a destinatario disponibile al primo invio
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
    And Si seleziona la notifica mittente
    And Aspetta 60 secondi
    And Si attende completamento notifica "Consegnata"
    And Logout da portale mittente