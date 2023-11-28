Feature: Mittente genera Api Key senza gruppo

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @TA_MittenteGeneraApiKeySenzaGruppo
  @mittente
  @ApikeyMittente


  Scenario: Mittente genera Api Key senza gruppo
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire il nome "testAutomationFE" per l Api Key
    And Nella sezione genera Api Key cliccare bottone continua
    And Si visualizza correttamente la pagina di conferma
    And Nella pagina di conferma cliccare sul bottone Torna a API key
    Then Si visualizza correttamente l api key "testAutomationFE" nell elenco in stato attivo
    And Logout da portale mittente
