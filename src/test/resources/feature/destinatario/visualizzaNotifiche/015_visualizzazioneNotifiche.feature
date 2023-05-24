Feature: Il destinatario visualizza la sezione notifiche

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

  @TestSuite
 @test15

  Scenario:
    When Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone Notifiche
    And Si visualizza correttamente la Pagina Notifiche Destinatario
    And Nella Pagina Notifiche Destinatario si visualizzano correttamente i filtri di ricerca
    And Nella Pagina Notifiche Destinatario si visualizza correttamente l elenco delle notifiche
    And  Logout da portale destinatario