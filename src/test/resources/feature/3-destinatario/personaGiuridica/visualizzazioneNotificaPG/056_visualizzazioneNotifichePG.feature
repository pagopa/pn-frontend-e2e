Feature: La persona fisica visualizza la sezione notifiche

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: La persona giuridica visualizza la sezione notifiche
    When Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And  Logout da portale persona giuridica