Feature: il destinatario inserisce una OTP sbagliato PEC

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

  @TestSuite
  @inserimentoPEC
  Scenario: il destinatario loggato inserisce un OTP sbagliato PEC
    When Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce la PEC del destinatario "destinatario"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "destinatario"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    Then Nella pagina I Tuoi Recapiti si controlla che la pec non sia stata aggiungta
    And Logout da portale destinatario

