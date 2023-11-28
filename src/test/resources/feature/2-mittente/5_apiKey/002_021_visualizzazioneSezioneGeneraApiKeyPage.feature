Feature: Mittente visualizza correttamente la sezione genera Api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
   When Login con mittente tramite token exchange
Then

    And Si visualizza correttamente la pagina Piattaforma Notifiche
    #And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @TA_MittenteVisualizzaGeneraApiKey
  @mittente
  @ApikeyMittente


  Scenario: Mittente visualizza correttamente la sezione genera Api Key
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    Then Si visualizza correttamente la sezione genera Api key
    And Logout da portale mittente
