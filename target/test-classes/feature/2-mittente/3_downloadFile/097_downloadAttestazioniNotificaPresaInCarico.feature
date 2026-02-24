Feature: il mittente download attestazione notifica presa in carico

  @TA_MittenteDownloadAttestazionePresaInCarico
  @DownloadFileMittente
  @NRT_Blocco_1
  Scenario: PN-9925 - il mittente scarica il file Attestazione opponibile a terzi: notifica presa in carico
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                                                                  |
