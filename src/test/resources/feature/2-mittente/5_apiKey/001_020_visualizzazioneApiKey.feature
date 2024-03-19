Feature: Mittente visualizza correttamente la pagina Api Key

  @TestSuite
  @TA_MittenteVisualizzazioneApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9228 - Mittente visualizza correttamente la pagina Api Key
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    And Si visualizza correttamente la lista delle Api Key generate
    And Si copia correttamente la Api Key cliccando sul bottone di copia
    Then Nella pagina Api Key posizionare il cursore sullo stato dell'operazione
    Then Nella pagina Api Key posizionare il cursuore sopra il numero gruppi
    And Logout da portale mittente



