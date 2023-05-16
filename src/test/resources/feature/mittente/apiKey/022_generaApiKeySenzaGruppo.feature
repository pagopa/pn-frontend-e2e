Feature: Mittente genera Api Key senza gruppo

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    #And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @secondCommitRun
  @testMittente
  @test22
  Scenario:
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire il nome "Simona" per l Api Key
    And Nella sezione genera Api Key cliccare bottone continua
    And Si visualizza correttamente la pagina di conferma
    And Nella pagina di conferma cliccare sul bottone Torna a API key
    Then Si visualizza correttamente l api key "Simona" nell elenco in stato attivo
    And Logout da portale mittente
