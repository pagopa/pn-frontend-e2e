Feature: invio notifica con sequence

  @Parallel
  @WorkflowNotificaConSequence
  @NotificaConSequenceOKRetryRS

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@OK-Retry_RS] - Il mittente invia una notifica a destinatario con sequence
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Gaio Giulio Cesare           |
      | codiceFiscale    | CSRGGL44L13H501E             |
      | tipoDestinatario | PF                           |
      | indirizzo        | via @OK-Retry_RS |
      | numeroCivico     | 20                           |
      | comune           | MILANO                       |
      | provincia        | MI                           |
      | codicepostale    | 20147                        |
      | stato            | ITALIA                       |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende che lo stato della notifica diventi "Depositata"
    And Si attende completamento notifica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata A/R") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true
    And Logout da portale mittente