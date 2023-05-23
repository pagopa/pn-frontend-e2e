Feature: Mittente seleziona l'opzione attiva api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @test26

  Scenario: Mittente seleziona l'opzione attiva Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key bloccata presente in elenco
    And Nella pagina Api Key si clicca sulla voce attiva del menu Api Key
    And Nella pagina Api Key si visualizza il pop up attiva Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato attiva
    And Logout da portale mittente
