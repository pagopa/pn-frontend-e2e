Feature: Mittente genera Api Key con gruppo

  @TestSuite
  @TA_MittenteGeneraApiKeyConGruppo
  @mittente
  @ApikeyMittente

  Scenario: PN-9231 - Mittente genera Api Key con gruppo
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire il nome "testAutomationFE" per l Api Key
    And Nella sezione genera Api Key inserire un gruppo
    And Nella sezione genera Api Key cliccare bottone continua
    And Si visualizza correttamente la pagina di conferma
    And Nella pagina di conferma cliccare sul bottone Torna a API key
    Then Si visualizza correttamente l api key "testAutomationFE" nell elenco in stato attivo
    And Logout da portale mittente
