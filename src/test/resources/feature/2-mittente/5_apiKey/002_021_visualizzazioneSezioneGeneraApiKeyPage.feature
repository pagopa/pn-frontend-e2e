Feature: Mittente visualizza correttamente la sezione genera Api Key

  @TestSuite
  @TA_MittenteVisualizzaGeneraApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9229 - Mittente visualizza correttamente la sezione genera Api Key
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    Then Si visualizza correttamente la sezione genera Api key
    And Logout da portale mittente
