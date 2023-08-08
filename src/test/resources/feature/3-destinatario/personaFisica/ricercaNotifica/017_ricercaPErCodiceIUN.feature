Feature: Ricerca persona fisica per Codice IUN
  #Il persona fisica fa un ricerca per persona fisica

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then  Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @test17
  @

  Scenario: Ricerca persona fisica per Codice IUN
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra persona fisica
    Then Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotifica"
    And  Logout da portale persona fisica