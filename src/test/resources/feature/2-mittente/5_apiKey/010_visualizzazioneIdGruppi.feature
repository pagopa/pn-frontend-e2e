Feature: Mittente seleziona l'opzione visualizza ID gruppo

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @fase2Test22
    @TestSuite

    @try
  Scenario: Mittente seleziona l'opzione visualizza ID gruppo
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza id gruppo del menu Api Key
    And Nella pagina Api Key si visualizza il pop up Gruppi associati alla API
    Then Nella pop up cliccare sul tasto chiudi
    And Si visualizza correttamente la pagina Api Key
    And Logout da portale mittente
