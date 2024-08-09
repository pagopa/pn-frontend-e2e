Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFricercaPerData
  @PF
  @PFRicercaNotifica

  Scenario: PN-9224-A29 - La persona fisica fa una ricerca per date
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale
      | annoDa   | 2023 |
      | meseDa   | 11   |
      | giornoDa | 7    |
      | annoA    | 2023 |
      | meseA    | 11   |
      | giornoA  | 9    |
    And Cliccare sul bottone Filtra persona fisica
    Then Vengono visualizzate correttamente le notifiche comprese nell'arco temporale inserito
    And Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina
    And Logout da portale persona fisica
