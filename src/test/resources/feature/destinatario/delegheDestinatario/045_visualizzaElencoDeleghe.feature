Feature: Il destinatario aggiunge una nuova delega

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test45
  Scenario:
    When Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe
    And Nella sezione Deleghe si visualizza il titolo
    And Nella sezione Deleghe si visualizza il sottotitolo
    And Nella sezione Deleghe si visualizza il bottone aggiungi una delega
    Then Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati
    And Nella sezione Deleghe si visualizzano tutti i campi dell'elenco delle deleghe a carico dell'utente
    And Logout da portale destinatario

