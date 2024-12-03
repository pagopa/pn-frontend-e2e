Feature: persona fisica aggiunge una delega dall'elenco degli enti radice

  @TestSuite
  @TA_PFaggiuntaDelegaEnteRadice
  @DeleghePF
  @PF

  Scenario:PN-10425 - La persona fisica aggiunge una delega dall'elenco enti radice
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia         |
      | cognome       | Borgia           |
      | codiceFiscale | BRGLRZ80D58H501Q |
      | ente          | Comune di Verona |
    Then Nella sezione della nuova delega si sceglie la visualizzazione delle notifiche da parte di: "solo enti selezionati"
    And Si verifica che nell'elenco degli enti sono presenti solamente enti radice
      | Agenzia delle Entrate        |
      | Comune di Afragola           |
      | Comune di Caserta            |
      | Comune di Cotronei           |
      | Comune di Mercenasco         |
      | Comune di Palmanova          |
      | Comune di Trivignano Udinese |
      | Comune di Vibo Valentia      |
      | Istituto Nazionale           |
      | Mercurio Riscossioni         |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma
    And Aspetta 10 secondi
    And Nella sezione Deleghe si clicca sul menu dei delegati
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    Then Si controlla che non ci sia più una delega
    And Logout da portale persona fisica
