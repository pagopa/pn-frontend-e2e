Feature: il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si verifica l'esistenza della notifica con il codice IUN
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Si verifica che la notifica sia nello stato consegnata

  @TestSuite
  @TA_MittenteDownloadAttestazioneMancatoRicapDigitale
  @mittente
  @DownloadFileMittente

  Scenario:il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: mancato recapito digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_mancato_recapito_digitale"
    And Logout da portale mittente