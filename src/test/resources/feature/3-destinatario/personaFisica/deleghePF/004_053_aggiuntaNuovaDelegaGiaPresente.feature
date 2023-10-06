Feature: persona fisica aggiunge una delega allo stesso delegato

  Background: login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @test53

  Scenario: il persona fisica aggiunge una delega allo stesso delegato
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega "nuova_delega"
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Le Tue Deleghe
    And Nella sezione Le Tue Deleghe inserire i dati "nuova_delega"
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta
    And Logout da portale persona fisica
