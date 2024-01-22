Feature: il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale

  @TestSuite
  @TA_MittenteDownloadAttestazioneMancatoRicapDigitale
  @mittente
  @DownloadFileMittente

  Scenario:il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si verifica l'esistenza della notifica con il codice IUN
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Si verifica che la notifica sia nello stato consegnata
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: mancato recapito digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_mancato_recapito_digitale"
    And Logout da portale mittente