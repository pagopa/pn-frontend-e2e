Feature: persona fisica scarica attestazioni all'interno di una notifica scaduta da oltre 120gg

  @TestSuite
  @TA_PGDownloadAttestazioniOltre120gg
  @PG

  Scenario: [TA-FE PG SCARICA ATTESTAZIONE SCADUTA]- persona giuridica scarica attestazione scaduta da oltre 120 gg
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale
      | annoDa   | 2022 |
      | meseDa   | 7  |
      | giornoDa | 1    |
      | annoA    | 2023 |
      | meseA    | 7   |
      | giornoA  |22    |
    And Cliccare sul bottone Filtra persona fisica
    And Si visualizzano correttamente le notifiche in elenco paginato
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica
    And Si clicca sul documento Attestazione scaduta
    And Si controlla che esista pop up scadenza
    And Logout da portale persona giuridica