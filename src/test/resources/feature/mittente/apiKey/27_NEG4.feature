Feature: Mittente seleziona CTA annulla in ruota api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite
  @staiNegativo
  @robiOffre
  @test25

  Scenario: Mittente seleziona CTA annulla in ruota api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pagina Api Key si visualizza il pop up ruota Api Key
    And Nella pop up cliccare sul tasto annulla
    And Logout da portale mittente