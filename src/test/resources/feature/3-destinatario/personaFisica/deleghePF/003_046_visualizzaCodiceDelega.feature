Feature: Il persona fisica visualizza il codice  di una delega

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @test46


  Scenario: Il persona fisica visualizza il codice  di una delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega "nuova_delega"
    And Nella sezione Deleghe si clicca sul menu della delega "nuova_delega"
    And Nella sezione Deleghe si sceglie l'opzione mostra codice
    Then Si clicca sul bottone chiudi
    And Logout da portale persona fisica
