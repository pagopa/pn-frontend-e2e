Feature: persona fisica scarica attestazioni all'interno di una notifica scaduta da oltre 120gg

  @TestSuite
  @TA_PFDownloadAttestazioniOltre120gg
  @PF
@120gg
  Scenario: [TA-FE PF SCARICA ATTESTAZIONE SCADUTA]- persona fisica scarica attestazione scaduta da oltre 120 gg
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale di maggiore di 120 giorni
   #  And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale
     #  | annoDa   | 2022 |
    #   | meseDa   | 7  |
     #  | giornoDa | 1    |
     #  | annoA    | 2023 |
      # | meseA    | 12   |
      # | giornoA  |27    |
    And Cliccare sul bottone Filtra persona fisica
    Then Vengono visualizzate correttamente le notifiche comprese nell'arco temporale inserito
    And Cliccare sulla notifica  maggiore di 120 giorni
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si clicca sul documento Attestazione scaduta 1
    And Si controlla che esista pop up scadenza
    And Logout da portale persona fisica