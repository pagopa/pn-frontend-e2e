Feature: Mittente seleziona l'opzione visualizza ID gruppo

  @TestSuite
  @TA_MittenteVisualizzaIDGroup
  @mittente
  @ApikeyMittente

  Scenario: PN-9236 - Mittente seleziona l'opzione visualizza ID gruppo
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza id gruppo del menu Api Key
    And Nella pagina Api Key si visualizza il pop up Gruppi associati alla API
    Then Nella pop up cliccare sul tasto chiudi
    And Si visualizza correttamente la pagina Api Key
    And Logout da portale mittente
