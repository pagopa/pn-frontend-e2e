Feature: La persona fisica visualizza la sezione notifiche

  @TA_PFvisualizzaNotifiche_015
  @NRT_Blocco_2

  Scenario:PN-9184 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Si visualizza correttamente la Pagina Notifiche persona fisica
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    And Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche