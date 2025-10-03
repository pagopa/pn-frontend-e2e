Feature: Ricerca notifica per periodo temporale persona fisica

  @TA_PFricercaPerData
  @NRT_Blocco_2

  Scenario: PN-9224-A29 - La persona fisica fa una ricerca per date
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale
    And Cliccare sul bottone Filtra persona fisica
    Then Vengono visualizzate correttamente le notifiche comprese nell'arco temporale inserito
    And Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina
