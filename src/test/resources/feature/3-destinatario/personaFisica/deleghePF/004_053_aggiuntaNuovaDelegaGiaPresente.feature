Feature: persona fisica aggiunge una delega allo stesso delegato

  @TestSuite
  @TA_PFaggiuntaDelegaGiaPresente
  @DeleghePF
  @PF

  Scenario:PN-9431 - La persona fisica aggiunge una delega allo stesso delegato
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega "nuova_delega"
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Le Tue Deleghe
    And Nella sezione Le Tue Deleghe inserire i dati "nuova_delega"
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta
    And Logout da portale persona fisica
