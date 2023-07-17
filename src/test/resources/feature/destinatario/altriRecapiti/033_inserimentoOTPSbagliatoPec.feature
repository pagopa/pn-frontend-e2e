Feature: il destinatario inserisce una OTP sbagliato PEC

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login portale destinatario tramite request method
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test33
    @restLogin

  Scenario: il destinatario loggato inserisce un OTP sbagliato PEC
    When Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce la PEC del destinatario "destinatario"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella pagina I Tuoi Recapiti clicca sul bottone conferma
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
    And Logout da portale destinatario

