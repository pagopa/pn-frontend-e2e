Feature: persona fisica scarica attestazioni all'interno di una notifica scaduta da oltre 120gg

  @TestSuite
  @TA_PFDownloadAttestazioniOltre120gg
  @PF
  @120gg
  @TA_Download
  Scenario: [TA-FE PF SCARICA ATTESTAZIONE SCADUTA]- persona fisica scarica attestazione scaduta da oltre 120 gg
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale di maggiore di 120 giorni
    And Cliccare sul bottone Filtra persona fisica
    Then Si visualizzano correttamente le notifiche in elenco paginato
    And Cliccare sulla notifica  maggiore di 120 giorni
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si clicca sul documento Attestazione scaduta