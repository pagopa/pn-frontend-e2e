Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @NotificaConRaccomandataAssenzaEventoDecedutoAR
  @NRT
  @Deceduto_aws

  Scenario: [DECEDUTO_AR_QA-5364] - Il mittente invia una notifica con raccomandata semplice a un destinatario deceduto
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
      | domicilioDigitale | prova@FAIL.IT                     |
      | indirizzo        | Via @FAIL_DECEDUTO_RS              |
      | numeroCivico     | 20                                 |
      | comune           | MILANO                             |
      | provincia        | MI                                 |
      | codicepostale    | 20147                              |
      | stato            | ITALIA                             |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
    And Aspetta 400 secondi
    #And Si controlla lo stato timeline in dettaglio notifica
    #  | xpathStato   | //p[contains(text(),"La raccomandata semplice") and contains(text(),"non è stata consegnata perché il destinatario è deceduto")] |
    #  | vediDettagli | true |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata semplice") and contains(text(),"stata stampata ed imbustata")] |
      | vediDettagli | true |
    #And Si controlla che non ci sia un evento di destinatario deceduto