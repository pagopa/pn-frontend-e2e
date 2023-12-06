Feature: il mittente download attestazione notifica presa in carico

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteDownloadAttestazionePresaInCarico
  @mittente
  @DownloadFile

  Scenario: il mittente scarica il file Attestazione opponibile a terzi: notifica presa in carico
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_notifica_presa_in_carico"
    And Logout da portale mittente
