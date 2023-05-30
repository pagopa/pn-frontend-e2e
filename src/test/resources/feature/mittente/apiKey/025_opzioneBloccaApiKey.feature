Feature: Mittente seleziona l'opzione blocca api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite

  @test25

  Scenario: Mittente seleziona l'opzione blocca Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pagina Api Key si visualizza il pop up blocca Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato bloccata
    And Logout da portale mittente

