Feature: Mittente effetua una ricerca notifiche per CF sbagliato
  #Mittente loggato effettua una ricerca

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then

  @TA_MittenteRicercaPerCFErrato
  @TestSuite
  @mittente
  @ricercaNatoficheMittente


  Scenario: Mittente loggato effettua una ricerca per CF sbagliato
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale sbagliato "QWERTY123"
    Then Nella pagina Piattaforma Notifiche si controlla che si visualizza il messaggio di errore codice fiscale
    And Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo
    And Logout da portale mittente