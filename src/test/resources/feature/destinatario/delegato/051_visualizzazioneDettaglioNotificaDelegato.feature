Feature: il delegato accede al dettaglio Notifiche

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente


  @TestSuite
  @test50
  Scenario: il delegato accede al dettaglio Notifiche
    When pagina Piattaforma  Notifiche Destinatario cliccare sul nome del delegante "destinatario"
    And Si visualizza correttamente la pagina Notifiche Destinatario con notifiche del delegante
    And Il destinatario clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica destinatario
    And Logout da portale destinatario