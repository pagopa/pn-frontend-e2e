Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9184 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Si visualizza correttamente la Pagina Notifiche persona fisica
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca, banner inserimento recapit,elenco delle notifiche ricevute
    And Nella Pagina Notifiche persona fisica si visualizzano correttamen
    And Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And  Logout da portale persona fisica