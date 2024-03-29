Feature: Mittente seleziona l'opzione ruota api Key

  @TestSuite
  @TA_MittenteRuotaApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9235 - Mittente seleziona l'opzione ruota Api Key
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pagina Api Key si visualizza il pop up ruota Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato ruota
    And Logout da portale mittente

