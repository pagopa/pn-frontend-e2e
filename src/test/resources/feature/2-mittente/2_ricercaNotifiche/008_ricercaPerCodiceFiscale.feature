Feature: Mittente effetua una ricerca notifiche per CF

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteRicercaNotificaPerCF
  @mittente
  @ricercaNatoficheMittente


  Scenario: PN-9217 - Mittente loggato effettua una ricerca per CF
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario "personaFisica"
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente