Feature: il mittente effettua il download attestazione opponibile a terzi avvenuto successo

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina piattaforma Notifiche selezionare lo stato notifica "Avvenuto accesso"
    And Cliccare sul bottone Filtra

  @TestSuite
  @TA_MittenteDownloadAttestazioneAvvenutaSuccesso
  @mittente
  @DownloadFileMittente

  Scenario: il mittente effettua il download attestazione opponibile a terzi avvenuto successo
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: avvenuto accesso", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_avvenuto_accesso"
    And Logout da portale mittente