Feature: Primo tentativo di invio pec

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

  @test86

  Scenario: Primo tentativo di invio pec
    When Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Si visualizza il domicilio digitale di piattaforma
    And Si effettua il primo tentativo di invio pec
    Then Si visualizza la notifica in stato di invio in corso
    And Logout da portale destinatario

