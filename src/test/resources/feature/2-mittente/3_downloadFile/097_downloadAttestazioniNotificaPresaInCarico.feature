Feature: il mittente download attestazione notifica presa in carico

  @TestSuite
  @TA_MittenteDownloadAttestazionePresaInCarico
  @mittente
  @DownloadFileMittente

  Scenario: il mittente scarica il file Attestazione opponibile a terzi: notifica presa in carico
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_notifica_presa_in_carico"
    And Logout da portale mittente
