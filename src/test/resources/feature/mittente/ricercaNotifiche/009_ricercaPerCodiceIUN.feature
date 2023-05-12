Feature: Mittente effetua una ricerca notifiche per codice IUN

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @firstCommitRun
  @test9
  Scenario: Mittente loggato effettua una ricerca per codice IUN
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche SVIL
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotifica"
    And Logout da portale mittente