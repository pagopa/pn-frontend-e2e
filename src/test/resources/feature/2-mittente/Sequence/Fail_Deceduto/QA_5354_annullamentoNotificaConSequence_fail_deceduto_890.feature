Feature: invio notifica con sequence

  @annullamentoNotificaConSequenceDeceduto890
  @TestSuite
  @NRT_Blocco_3
  @Sequence_Deceduto

  Scenario: [DECEDUTO_890_QA-5354] - Il mittente invia una notifica a destinatario con sequence e viene annullata dopo essere passata allo stato Resa al mittente
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
      | modello         | 890                                                  |
      | documenti       | 1                                                    |
      | oggettoNotifica | Pagamento rata IMU per immobile in via DECEDUTO_890_QA-5354 |
      | costiNotifica   | false                                                |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E         |
      | tipoDestinatario | PF                       |
      | indirizzo        | via @FAIL_DECEDUTO_890   |
      | numeroCivico     | 20                       |
      | comune           | MILANO                   |
      | provincia        | MI                       |
      | codicepostale    | 20147                    |
      | stato            | ITALIA                   |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Resa al mittente"
    And Si annulla la notifica
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
#    And Aspetta 400 secondi
    And Attesa 400 secondi
    And Refresh pagina
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //span[contains(text(),"L'ente ha annullato l'invio della notifica")] |
      | vediDettagli | true                                                                  |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | false                                                                                            |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"non è stata consegnata perché il destinatario è deceduto")] |
      | vediDettagli | false                                                                                                                       |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Scansione del plico")] |
      | vediDettagli | false                                            |