Feature: invio notifica con sequence

  @Parallel
  @WorkflowNotificaConSequence
  @NotificaConSequenceOKPersonaAbilitata890

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@OK-PersonaAbilitata_890] - Il mittente invia una notifica a destinatario con sequence
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
      | indirizzo        | via @OK-PersonaAbilitata_890 |
      | numeroCivico     | 20                           |
      | comune           | MILANO                       |
      | provincia        | MI                           |
      | codicepostale    | 20147                        |
      | stato            | ITALIA                       |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Depositata"
    And Aspetta 400 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //span[contains(text(),"Avviso di ricevimento")] |
      | vediDettagli | false                                            |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata consegnata a una persona abilitata")] |
      | vediDettagli | false                                                                                                          |
    And Logout da portale mittente