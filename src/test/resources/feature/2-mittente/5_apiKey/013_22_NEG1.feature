Feature: Mittente genera Api Key senza inserire il nome dell api key

  @TestSuite
  @TA_MittenteGeneraApiKeySenzaNome
  @mittente
  @ApikeyMittente1

  Scenario: PN-9235 - Mittente genera Api Key senza inserire il nome dell api key
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire il nome "testAutomationFE" per l Api Key
    And Nella sezione genera Api Key cancellare il testo inserito
    Then Nella sezione genera si visualizza un messaggio di errore
    And Logout da portale mittente
