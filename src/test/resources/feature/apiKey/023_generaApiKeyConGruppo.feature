Feature: Mittente genera Api Key senza gruppo

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @generaApiKeyConGruppo
  Scenario:
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire un nome per l Api Key e un gruppo "000"
    And Nella sezione genera Api Key cliccare bottone continua
    And Si visualizza correttamente la pagina di conferma
    And Nella pagina di conferma cliccare sul bottone Torna a API key
    Then Si visualizza correttamente l'api key nell'elenco in stato attivo
    And Logout da portale mittente
