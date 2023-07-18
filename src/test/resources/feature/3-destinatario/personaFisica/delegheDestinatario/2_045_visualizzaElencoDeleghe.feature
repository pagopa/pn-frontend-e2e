Feature: Il persona fisica visualizza la sezione aggiungi una nuova delega

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

   @TestSuite
  @test45
   @restLogin

  Scenario: Il persona fisica visualizza la sezione aggiungi una nuova delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si visualizza il titolo
    And Nella sezione Deleghe si visualizza il sottotitolo
    And Nella sezione Deleghe si visualizza il bottone aggiungi una delega
    Then Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati
    And Logout da portale persona fisica

