Feature: il mittente effettua il download attestazione opponibile a terzi notifica digitale

  @TestSuite
  @TA_MittenteDownloadAttestazioneNotificaDigitale
  @mittente
  @DownloadFileMittente

  Scenario: PN-9926 - il mittente effettua il download attestazione opponibile a terzi notifica digitale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN di una persona giuridica
    And Cliccare sul bottone Filtra
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: notifica digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_notifica_digitale"
    And Logout da portale mittente