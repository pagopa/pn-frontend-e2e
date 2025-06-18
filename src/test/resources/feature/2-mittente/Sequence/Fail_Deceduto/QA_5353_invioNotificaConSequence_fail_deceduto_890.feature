Feature: invio notifica con sequence

  @NotificaConSequenceDeceduto890
  @TestSuite
  @NRT_Blocco_3
  @Sequence_Deceduto

  Scenario: [DECEDUTO_890_QA-5353] - Il mittente invia una notifica a destinatario con sequence
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                                             |
      | documenti       | 1                                               |
      | oggettoNotifica | Pagamento rata IMU per immobile in via DECEDUTO_890_QA-5353 |
      | costiNotifica   | false                                           |
    And Si aggiunge un destinatario alla notifica
      | tipoDestinatario  | PG                     |
      | nomeCognome       | Convivio Spa           |
      | codiceFiscale     | 27957814470            |
      | at                | Presso                 |
      | indirizzo         | Via @FAIL_DECEDUTO_890 |
      | dettagliIndirizzo | Scala b                |
      | comune            | Milano                 |
      | dettagliComune    | Milano                 |
      | provincia         | MI                     |
      | codicePostale     | 20147                  |
      | stato             | Italia                 |
      | avvisoPagoPa      | 1                      |
      | F24               | 1                      |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Resa al mittente"
    And Aspetta 400 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"non è stata consegnata perché il destinatario è deceduto")] |
      | vediDettagli | false                                                                                                                       |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Scansione del plico")] |
      | vediDettagli | false                                            |