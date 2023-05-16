Feature: Ricerca Destinatario per Codice IUN
  #Il destinatario fa un ricerca per destinatario

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then  Home page destinatario viene visualizzata correttamente

  @TestSuite
  @secondCommitRun
  @testDestinatario
  @test17
  Scenario:
    When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
    And Nella pagina Piattaforma Notifiche  Destinatario inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra Destinatario
    Then Nella pagina Piattaforma Notifiche Destinatario vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotifica"
    And  Logout da portale destinatario