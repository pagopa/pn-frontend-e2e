Feature: Mittente seleziona l'opzione visualizza ID gruppo

  @TestSuite
  @TA_MittenteVisualizzaIDGroup
  @ApikeyMittente
 @NRT
  @integrazioneApi
  Scenario: PN-9236 - Mittente seleziona l'opzione visualizza ID gruppo
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    And Nella pagina Api Key si clicca sul bottone genera Api Key
    And Si visualizza correttamente la sezione genera Api key
    And Nella sezione genera Api Key inserire il nome "testAutomationFE" per l Api Key
    And Nella sezione genera Api Key inserire un gruppo
    And Nella sezione genera Api Key cliccare bottone continua
    And Si visualizza correttamente la pagina di conferma
    And Si copia e salva API key generata
    And Nella pagina di conferma cliccare sul bottone Torna a API key
    Then Si visualizza correttamente l api key "testAutomationFE" nell elenco in stato attivo
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza id gruppo del menu Api Key
    And Nella pagina Api Key si visualizza il pop up Gruppi associati alla API
    Then Nella pop up cliccare sul tasto chiudi
    And Si visualizza correttamente la pagina Api Key
