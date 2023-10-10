Feature: Mittente effetua una ricerca notifiche per codice IUN

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test9

  Scenario: Mittente loggato effettua una ricerca per codice IUN
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN valido
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica
    And Logout da portale mittente