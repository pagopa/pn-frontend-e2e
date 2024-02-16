Feature:La persona fisica visualizza le notifiche in elenco

  @TestSuite
  @TA_PFPaginazioneNotifiche3
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9209-C27 - La persona fisica posizionarsi su una pagina diversa e poi applica un filtro di ricerca
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    Then Si visualizzano correttamente le notifiche in elenco paginato
    And Si visualizzano le notifiche dalla piu recente
    And Si clicca su pagina diversa dalla prima
    And Nella pagina Piattaforma Notifiche persona giuridica inserire un arco temporale
    And Cliccare sul bottone Filtra persona giuridica
    Then Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And Si verifica che visualizza la prima pagina
    And Logout da portale persona fisica

