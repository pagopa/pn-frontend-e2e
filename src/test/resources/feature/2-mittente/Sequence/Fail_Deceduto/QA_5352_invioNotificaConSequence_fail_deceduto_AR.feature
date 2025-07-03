Feature: invio notifica con sequence

  @NotificaConSequenceDecedutoAR
  @TestSuite
  @NRT_Blocco_3
  @Sequence_Deceduto

  Scenario: [DECEDUTO_AR_QA-5352] - Il mittente invia una notifica a destinatario con sequence
    # Rimozione preventiva recapiti per permettere la ricezione delle sequence
    Given Login Page persona fisica test viene visualizzata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    # Esecuzione scenario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                                              |
      | documenti       | 1                                               |
      | oggettoNotifica | Pagamento rata IMU per immobile in via DECEDUTO_AR_QA-5352 |
      | costiNotifica   | false                                           |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Gaio Giulio Cesare    |
      | codiceFiscale    | CSRGGL44L13H501E      |
      | tipoDestinatario | PF                    |
      | indirizzo        | via @FAIL_DECEDUTO_AR |
      | numeroCivico     | 20                    |
      | comune           | MILANO                |
      | provincia        | MI                    |
      | codicepostale    | 20147                 |
      | stato            | ITALIA                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Resa al mittente"
#    And Aspetta 400 secondi
    And Attesa 400 secondi
    And Refresh pagina
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata A/R") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata A/R") and contains(text(),"non è stata consegnata perché il destinatario è deceduto")] |
      | vediDettagli | false                                                                                                                       |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Scansione del plico")] |
      | vediDettagli | false                                            |