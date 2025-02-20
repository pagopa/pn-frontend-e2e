Feature: invio notifica con sequence

  @Parallel
  @WorkflowNotificaConSequence
  @NotificaConSequenceDecedutoAR

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-XXXXXXXX] - Il mittente invia una notifica a destinatario con sequence
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                 |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU per immobile in via XXXXXXXX |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Gaio Giulio Cesare                 |
      | codiceFiscale    | CSRGGL44L13H501E                   |
      | tipoDestinatario | PF                                 |
      | indirizzo        | via XXXXXXXX |
      | numeroCivico     | 20                                 |
      | comune           | MILANO                             |
      | provincia        | MI                                 |
      | codicepostale    | 20147                              |
      | stato            | ITALIA                             |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Avvenuto accesso"
    And Aspetta 30 secondi