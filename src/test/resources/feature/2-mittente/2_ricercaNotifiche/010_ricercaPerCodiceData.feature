Feature: Mittente effetua una ricerca notifiche per Data

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteRicercaPerData
  @mittente
  @ricercaNatoficheMittente

  Scenario: PN-9220 - Mittente loggato effettua una ricerca per periodo temporale
    When Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente