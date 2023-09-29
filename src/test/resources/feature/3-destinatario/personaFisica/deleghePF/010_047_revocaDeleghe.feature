Feature: Il persona fisica revoca una delega

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome "nuova_delega"
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Le Tue Deleghe
    And Nella sezione Le Tue Deleghe inserire i dati "nuova_delega"
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "nuova_delega"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe

  @TestSuite
  @test47

  Scenario: Il persona fisica revoca una delega
    When Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella sezione Deleghe si clicca sul menu della delega "nuova_delega"
    And Nella sezione Deleghe si sceglie l'opzione revoca
    Then Si conferma l'azione scegliendo revoca la delega
    And Logout da portale persona fisica









































































































































































































