Feature: il mittente effettua il download attestazione opponibile a terzi avvenuto successo

  @TestSuite
  @TA_MittenteDownloadAttestazioneAvvenutaSuccesso
  @mittente
  @DownloadFileMittente

  Scenario: PN-9647 - il mittente effettua il download attestazione opponibile a terzi avvenuto successo
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina piattaforma Notifiche selezionare lo stato notifica "Avvenuto accesso"
    And Cliccare sul bottone Filtra
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: avvenuto accesso")] |
      | vediDettagli | false                                          |
   And Logout da portale mittente