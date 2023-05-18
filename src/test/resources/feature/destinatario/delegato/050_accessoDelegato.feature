Feature: il delegato accede alle sezione Notifiche

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente


  @TestSuite
  @test50
  Scenario: il delegato accede alla sezione notifiche
    When pagina Piattaforma  Notifiche Destinatario cliccare sul nome del delegante "destinatario"
    And Si visualizza correttamente la pagina Notifiche Destinatario con notifiche del delegante
    And Logout da portale destinatario