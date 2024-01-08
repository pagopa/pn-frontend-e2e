Feature: Mittente visualizza correttamente la pagina Api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteVisualizzazioneApiKey
  @mittente
  @ApikeyMittente


  Scenario: PN-9228 - Mittente visualizza correttamente la pagina Api Key
    When Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    And Si visualizza correttamente la lista delle Api Key generate
    Then Nella pagina Api Key posizionare il cursore sullo stato dell'operazione
    And Logout da portale mittente



