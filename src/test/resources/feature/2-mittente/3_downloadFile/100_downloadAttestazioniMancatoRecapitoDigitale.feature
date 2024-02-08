Feature: il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale

# DISABLED Temporary disabled until is fixed - the cause is due to the fact that the attachment is not present in the notification
  # and must be waited before to be downloaded
#  @TestSuite
#  @TA_MittenteDownloadAttestazioneMancatoRicapDigitale
#  @mittente
#  @DownloadFileMittente

  Scenario:il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si verifica l'esistenza della notifica con il codice IUN
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Si verifica che la notifica sia nello stato consegnata
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: mancato recapito digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione_opponibile_a_terzi_mancato_recapito_digitale"
    And Logout da portale mittente