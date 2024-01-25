Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFricercaPerData
  @PF
  @PFRicercaNotifica

  Scenario: PN-9224 - La persona fisica fa una ricerca per date
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale
    And Cliccare sul bottone Filtra persona fisica
    Then Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina
    And Logout da portale persona fisica