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
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Logout da portale mittente