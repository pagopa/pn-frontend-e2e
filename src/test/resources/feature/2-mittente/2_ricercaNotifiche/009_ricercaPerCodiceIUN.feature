Feature: Mittente effetua una ricerca notifiche per codice IUN

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteRicercaPerCodiceIUN
  @mittente
  @ricercaNatoficheMittente

  Scenario: Mittente loggato effettua una ricerca per codice IUN
    When Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN valido
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica
    And Logout da portale mittente