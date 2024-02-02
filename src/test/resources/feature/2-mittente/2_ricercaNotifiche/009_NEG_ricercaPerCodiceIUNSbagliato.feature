Feature: Mittente effetua una ricerca notifiche per codice IUN sbagliato

  @TA_MittenteRicercaCodiceIUNSbagliato
  @mittente
  @ricercaNatoficheMittente
  @TestSuite

  Scenario: PN-9322 - Mittente loggato effettua una ricerca per codice IUN sbagliato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice IUN sbagliato "NYUM-RLWQ-JDTP-202308"
    Then Nella pagina Piattaforma Notifiche si visualizza il messaggio di errore codice IUN
    And Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo
    And Logout da portale mittente