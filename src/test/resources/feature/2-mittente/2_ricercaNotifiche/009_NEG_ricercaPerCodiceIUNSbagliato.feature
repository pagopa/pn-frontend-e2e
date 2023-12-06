Feature: Mittente effetua una ricerca notifiche per codice IUN sbagliato

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
  @TA_MittenteRicercaCodiceIUNSbagliato
  @mittente
  @ricercaNatoficheMittente
  @TestSuite

  Scenario: Mittente loggato effettua una ricerca per codice IUN sbagliato
    When Nella pagina Piattaforma Notifiche inserire il codice IUN sbagliato "NYUM-RLWQ-JDTP-202308-"
    Then Nella pagina Piattaforma Notifiche si visualizza il messaggio di errore codice IUN
    And Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo
    And Logout da portale mittente