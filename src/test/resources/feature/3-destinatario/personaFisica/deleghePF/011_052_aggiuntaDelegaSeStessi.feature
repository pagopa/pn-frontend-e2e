Feature:La persona fisica aggiunge una delega a se stessi

  @TestSuite
  @TA_PFaggiuntaDelegaSeStesso
  @DeleghePF
  @PF

  Scenario:PN-9420 - La persona fisica aggiunge una delega a se stessi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Gaio Giulio       |
      | cognome       | Cesare            |
      | codiceFiscale | CSRGGL44L13H501E  |
      | ente          | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore
    And Logout da portale persona fisica