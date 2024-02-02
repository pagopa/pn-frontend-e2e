Feature: Mittente effetua una ricerca notifiche per CF sbagliato
  #Mittente loggato effettua una ricerca

  @TA_MittenteRicercaPerCFErrato
  @TestSuite
  @mittente
  @ricercaNatoficheMittente

  Scenario: PN-9321 - Mittente loggato effettua una ricerca per CF sbagliato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale sbagliato "QWERTY123"
    Then Nella pagina Piattaforma Notifiche si controlla che si visualizza il messaggio di errore codice fiscale
    And Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra sia attivo
    And Logout da portale mittente