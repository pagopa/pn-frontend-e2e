Feature: Il destinatario aggiunge una delega a se stessi

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test52

  Scenario: Il destinatario aggiunge una delega a se stessi
    When Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Le Tue Deleghe
    And Nella sezione Le Tue Deleghe inserire i dati "nuova_delega_err"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega a se stessi
    And Logout da portale destinatario