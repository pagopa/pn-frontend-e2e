Feature: Mittente effetua una ricerca notifiche per Data

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
    @ricercaNotificaPerData
  Scenario Outline: Mittente loggato effettua una ricerca per periodo temporale
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire una data da <DA> a <A>
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente

    Examples:
      | DA          | A             |
      | 01/04/2023  | 12/04/2023   |