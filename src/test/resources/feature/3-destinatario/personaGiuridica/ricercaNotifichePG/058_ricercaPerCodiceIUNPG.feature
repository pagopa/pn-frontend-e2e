Feature: Il persona giuridica ricerca per codice IUN

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  @TestSuite
  @fase2Test58
  @pg
  Scenario: Il persona giuridica ricerca per codice IUN
    When Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    When Si visualizza correttamente la Pagina Notifiche persona giuridica
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "datiNotificaPG"
    And Cliccare sul bottone Filtra persona giuridica
    Then Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotificaPG"
    And  Logout da portale persona giuridica

