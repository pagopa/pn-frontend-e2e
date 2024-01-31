Feature: La persona fisica visualizza la sezione notifiche

  # DISABLED Temporary disabled until the bug PN-9147 is fixed
#  @TestSuite
#  @TA_PGVisualizzaNotifiche
#  @VisualizzazioneNotifichePG
#  @PG

  Scenario: PN-9147 - La persona giuridica visualizza la sezione notifiche
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG "personaGiuridica"
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate "delegatoPG"
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And  Logout da portale persona giuridica