Feature: destinatario aggiunge una delega allo stesso delegato

  Background: login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login portale destinatario tramite request method
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

  @TestSuite
  @test53
  @restLogin


  Scenario: il destinatario aggiunge una delega allo stesso delegato
    When Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Le Tue Deleghe
    And Nella sezione Le Tue Deleghe inserire i dati "nuova_delega"
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta
    And Logout da portale destinatario
