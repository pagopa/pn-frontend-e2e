Feature: persona giuridica aggiunge una delega dall'elenco degli enti radice

  @TestSuite
  @TA_PGaggiuntaDelegaEnteRadice
  @DeleghePG
  @PG

  Scenario:PN-10429 - La persona giuridica aggiunge una delega dall'elenco enti radice
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Le Epistolae srl  |
      | codiceFiscale  | LELPTR04A01C352E  |
      | ente           | Comune di Palermo |
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
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    And Nella sezione Deleghe sezione Deleghe dell'impresa si controlla che non sia più presente la delega "nuovaDelegaPG"
    And Logout da portale persona fisica
