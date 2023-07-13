Feature: Mittente effetua una ricerca notifiche per Data

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test10

  Scenario: Mittente loggato effettua una ricerca per periodo temporale
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
   # And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente