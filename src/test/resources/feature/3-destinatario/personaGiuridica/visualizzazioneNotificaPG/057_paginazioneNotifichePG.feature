Feature: La persona fisica visualizza la sezione notifiche

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  @TestSuite
  @fase2Test57
  @pg
  Scenario: La persona giuridica visualizza la sezione notifiche
    When Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica
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