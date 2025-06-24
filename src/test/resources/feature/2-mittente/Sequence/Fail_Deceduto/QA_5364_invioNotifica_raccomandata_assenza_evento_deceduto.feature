Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @TAG_DECEDUTO_890_QA-5364
  @NRT_Blocco_3_prova_VAS_DEC
  @Sequence_Deceduto

  Scenario: [DECEDUTO_AR_QA-5364] - Il mittente invia una notifica con raccomandata semplice a un destinatario deceduto
    # Rimozione preventiva recapiti per permettere la ricezione delle sequence
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Rimuovi tutti i recapiti se esistono
    # Esecuzione scenario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                                              |
      | documenti       | 1                                               |
      | oggettoNotifica | Pagamento rata IMU per immobile in via DECEDUTO_AR_QA-5364 |
      | costiNotifica   | false                                           |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome       | Gaio Giulio Cesare    |
      | codiceFiscale     | CSRGGL44L13H501E      |
      | tipoDestinatario  | PF                    |
      | domicilioDigitale | prova@FAIL.IT         |
      | indirizzo         | Via @FAIL_DECEDUTO_RS |
      | numeroCivico      | 20                    |
      | comune            | MILANO                |
      | provincia         | MI                    |
      | codicepostale     | 20147                 |
      | stato             | ITALIA                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
    And Aspetta 400 secondi
    And Si visualizza testo nella timeline "invio via raccomandata semplice"
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata") and contains(text(),"stampata ed imbustata")] |
      | vediDettagli | false |