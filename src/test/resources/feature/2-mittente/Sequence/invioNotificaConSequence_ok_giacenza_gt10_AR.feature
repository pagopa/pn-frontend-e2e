Feature: invio notifica con sequence

  @Parallel
  @WorkflowNotificaConSequence
  @NotificaConSequenceOKGiacenzagt10AR

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@OK-Giacenza-gt10_AR] - Il mittente invia una notifica a destinatario con sequence
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                 |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Gaio Giulio Cesare  |
      | codiceFiscale    | CSRGGL44L13H501E    |
      | tipoDestinatario | PF                  |
      | indirizzo        | via @OK-Giacenza-gt10_AR |
      | numeroCivico     | 20                  |
      | comune           | MILANO              |
      | provincia        | MI                  |
      | codicepostale    | 20147               |
      | stato            | ITALIA              |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende che lo stato della notifica diventi "Depositata"
    And Si attende completamento notifica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata A/R") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"Il tentativo di consegna della raccomandata A/R") and contains(text(),"non è andato a buon fine per destinatario assente")] |
      | vediDettagli | false                                                                                                                                            |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La giacenza della raccomandata A/R ") and contains(text(),"è iniziata")] |
      | vediDettagli | false                                                                                         |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata A/R") and contains(text(),"è stata consegnata presso un punto di giacenza")] |
      | vediDettagli | false                                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"presso il punto di giacenza entro 10 giorni")] |
      | vediDettagli | false                                                               |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //span[contains(text(),"Ricevuta di consegna")] |
      | vediDettagli | false                                           |
    And Logout da portale mittente