Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PGPaginazioneNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: PN-9148 - La persona giuridica visualizza la sezione notifiche
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Si visualizzano le notifiche dalla piu recente
    And Si aggiorna la paginazione utilizzando le frecce
    And Si visualizza correttamente una pagina diversa dalla precedente
    And Ci si posiziona su una pagina differente attraverso i numeri
    And Si visualizza correttamente una pagina diversa dalla precedente
    And Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default
    And Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate
    Then Si visualizza un numero di pagine visualizzate uguale a quello selezionato
    And  Logout da portale persona giuridica