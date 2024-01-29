Feature: Mittente visualizza correttamente la pagina Api Key

  @TestSuite
  @TA_MittenteVisualizzazioneApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9228 - Mittente visualizza correttamente la pagina Api Key
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    And Si visualizza correttamente la lista delle Api Key generate
    Then Nella pagina Api Key posizionare il cursore sullo stato dell'operazione
    And Logout da portale mittente



