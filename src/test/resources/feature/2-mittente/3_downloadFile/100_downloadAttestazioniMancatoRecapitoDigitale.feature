Feature: il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale

  @parallel
  @TA_MittenteDownloadAttestazioneMancatoRicapDigitale
  @mittente
  @DownloadFileMittente

  Scenario: PN-9927 - il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo         | Via Roma  |
      | nomeCognome       | Gaio Giulio Cesare        |
      | codiceFiscale     | CSRGGL44L13H501E          |
      | tipoDestinatario  | PF                        |
      | domicilioDigitale | prova@fail.it    |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: mancato recapito digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_mancato_recapito_digitale"
    And Logout da portale mittente