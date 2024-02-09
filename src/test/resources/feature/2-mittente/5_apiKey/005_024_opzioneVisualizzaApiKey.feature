Feature: Mittente seleziona l'opzione visualizza api Key

  @TestSuite
  @TA_MittenteOpzioneVisualizzaApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9232 - Mittente seleziona l'opzione visualizza api Key
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    Then Nel pop up visualizza cliccare sul tasto chiudi
    And Logout da portale mittente