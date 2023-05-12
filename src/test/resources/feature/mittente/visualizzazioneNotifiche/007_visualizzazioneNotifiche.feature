Feature: Mittente visualizza correttamente la pagina notifiche

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test7
  Scenario:
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca
    Then Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche
    And Logout da portale mittente



