Feature: Mittente seleziona l'opzione visualizza api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key

  @TestSuite

  Scenario: Mittente seleziona l'opzione blocca Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pagina Api Key si visualizza corretamente il pop up visualizza Api Key
    And Nel pop up il codice api key è uguale al codice in anteprima e si salva "mittente"
    And Nella pop up cliccare sul tasto chiudi
    Then Nella pagina Api Key si visualizza la notifica selezionata nello stato bloccata
    And Logout da portale mittente
