Feature: Il destinatario visualizza il codice  di una delega

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login portale destinatario tramite request method
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test46
  @restLogin

  Scenario: Il destinatario visualizza il codice  di una delega
    When Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella sezione Deleghe si clicca sul menu della delega
    And Nella sezione Deleghe si sceglie l'opzione mostra codice
    Then Si clicca sul bottone chiudi
    And Logout da portale destinatario
