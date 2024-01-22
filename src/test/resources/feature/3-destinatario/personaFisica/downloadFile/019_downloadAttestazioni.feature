Feature: persona fisica scarica attestazioni all'interno di una notifica

  @TestSuite
  @test19
  @TA_PFDownloadAttestazioni
  @PF

  Scenario: PN-9239 - persona fisica scarica attestazione
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido
    And Cliccare sul bottone Filtra persona fisica
    When La persona fisica clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto "datiNotifica"
    And Si clicca sul opzione Vedi Dettaglio
    And Logout da portale persona fisica