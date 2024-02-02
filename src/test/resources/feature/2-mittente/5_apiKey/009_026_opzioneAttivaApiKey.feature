Feature: Mittente seleziona l'opzione attiva api Key

  @TestSuite

  @TA_MittenteAttivaApiKey
  @mittente
  @ApikeyMittente

  Scenario: PN-9234 - Mittente seleziona l'opzione attiva Api Key
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key bloccata presente in elenco
    And Nella pagina Api Key si clicca sulla voce attiva del menu Api Key
    And Nella pagina Api Key si visualizza il pop up attiva Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato attiva
    And Logout da portale mittente
