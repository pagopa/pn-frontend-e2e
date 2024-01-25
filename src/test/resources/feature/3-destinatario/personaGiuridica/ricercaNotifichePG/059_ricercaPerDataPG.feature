Feature: La persona giuridica ricerca per periodo temporale

  @TestSuite
  @TA_PGricercaNotificaPerData
  @RicercaNotifichePG
  @PG

  Scenario: PN-9150 - La persona giuridica ricerca per periodo temporale
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche persona giuridica inserire un arco temporale
    And Cliccare sul bottone Filtra persona giuridica
    Then Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And  Logout da portale persona giuridica