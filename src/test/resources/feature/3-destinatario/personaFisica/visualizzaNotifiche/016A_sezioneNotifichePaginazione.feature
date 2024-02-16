Feature:La persona fisica visualizza le notifiche in elenco

  @TestSuite
  @TA_PFPaginazioneNotifiche1
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9209-A27 - La persona fisica cambia la pagina utilizzando le frecce
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    Then Si visualizzano correttamente le notifiche in elenco paginato
    And Si visualizzano le notifiche dalla piu recente
    And Si aggiorna la paginazione utilizzando le frecce
    Then Si visualizza correttamente la prossima pagina
    And Logout da portale persona fisica

