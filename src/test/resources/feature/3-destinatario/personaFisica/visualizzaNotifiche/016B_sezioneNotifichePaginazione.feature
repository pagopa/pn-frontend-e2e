Feature:La persona fisica visualizza le notifiche in elenco

  @TestSuite
  @TA_PFNumeroNotificheVisualizzati
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9209-B27 - La persona fisica modificare il numero di elementi visualizzati per pagina
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    Then Si visualizzano correttamente le notifiche in elenco paginato
    And Si visualizzano le notifiche dalla piu recente
    And Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default
    And Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate
    Then Si visualizza un numero di pagine visualizzate uguale a quello selezionato
    And Logout da portale persona fisica

