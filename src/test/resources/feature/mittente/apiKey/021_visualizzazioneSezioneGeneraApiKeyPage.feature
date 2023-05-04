Feature: Mittente visualizza correttamente la sezione genera Api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @visualizzazioneSezioneGeneraApiKeyPage
  Scenario:
    When Nella pagina Api Key Si clicca sul bottone genera Api Key
    Then Si visualizza correttamente la sezione genera Api key
    And Logout da portale mittente
