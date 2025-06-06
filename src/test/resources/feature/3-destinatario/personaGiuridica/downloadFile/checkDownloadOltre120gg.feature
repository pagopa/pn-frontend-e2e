Feature: persona fisica scarica attestazioni all'interno di una notifica scaduta da oltre 120gg

  @TestSuite
  @TA_PGDownloadAttestazioniOltre120gg
  @NRT
  Scenario: [TA-FE PG SCARICA ATTESTAZIONE SCADUTA]- persona giuridica scarica attestazione scaduta da oltre 120 gg
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale di maggiore di 120 giorni
    And Cliccare sul bottone Filtra persona giuridica
    And Si visualizzano correttamente le notifiche in elenco paginato
    And Cliccare sulla notifica  maggiore di 120 giorni
    Then Si visualizza correttamente la section Dettaglio Notifica
    And Si clicca sul documento Attestazione scaduta