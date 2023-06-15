Feature: Mittente visualizza correttamente la pagina Api Key

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

    @TestSuite
    @test20
    @quintaConsegna

  Scenario: Mittente visualizza correttamente la pagina Api Key
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    And Si visualizza correttamente la lista delle Api Key generate
    Then Nella pagina Api Key posizionare il cursore sullo stato dell'operazione
    And Logout da portale mittente



