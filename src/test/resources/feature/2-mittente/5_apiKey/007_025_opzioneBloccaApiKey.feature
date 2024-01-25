Feature: Mittente seleziona l'opzione blocca api Key

  @TestSuite
  @TA_MittenteBloccaApikey
  @mittente
  @ApikeyMittente

  Scenario: PN-9233 - Mittente seleziona l'opzione blocca Api Key
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pagina Api Key si visualizza il pop up blocca Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato bloccata
    And Logout da portale mittente

