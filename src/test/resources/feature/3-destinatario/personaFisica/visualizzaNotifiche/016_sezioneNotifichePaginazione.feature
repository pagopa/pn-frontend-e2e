Feature:La persona fisica visualizza le notifiche in elenco

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite token exchange "personaFisica"
    Then pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente

    @TestSuite
    @TA_PFPaginazioneNotifiche
    @PFvisualizzaNotifiche
    @PF


    Scenario:La persona fisica visualizza le notifiche in elenco
      When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
      Then Si visualizzano correttamente le notifiche in elenco paginato
      And Si visualizzano le notifiche dalla piu recente
      And Si aggiorna la paginazione utilizzando le frecce
      And Si visualizza correttamente una pagina diversa dalla precedente
      And Ci si posiziona su una pagina differente attraverso i numeri
      And Si visualizza correttamente una pagina diversa dalla precedente
      And Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default
      And Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate
      And Si visualizza un numero di pagine visualizzate uguale a quello selezionato
      And Logout da portale persona fisica

