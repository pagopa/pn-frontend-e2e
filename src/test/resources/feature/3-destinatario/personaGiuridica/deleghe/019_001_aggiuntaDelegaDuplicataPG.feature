Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @DeleghePG
  @PG
  @DeleghePFPG1
  @deleghe2
  @TA_PGdeleganteAggiuntaDelegaDuplicata
  @GestioneErrori
  @NRT_Blocco_1
  Scenario: [PN-14926-PN_MANDATE_ALREADYEXISTS DELEGANTE PG AMMINISTRATORE] - Il delegante aggiunta delega duplicata PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Lucrezia Borgia"
    And Nella sezione Deleghe si crea una delega accettata per PG
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si accetta la delega accettata per PG

#    And PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Aspetta 5 secondi
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega senza revocare quella esistente
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Verona    |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    Then Nella sezione Le Tue Deleghe si visualizza il messaggio di errore delega gia aggiunta


  @TA_PGdeleganteAggiuntaDelegaPF
  Scenario: [DELEGANTE PG AMMINISTRATORE] - Il delegante aggiunta delega PF
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia         |
      | cognome       | Borgia           |
      | codiceFiscale | BRGLRZ80D58H501Q |
      | ente          | Comune di Verona |
    And Nella sezione Le Tue Deleghe inserire una data con formato errato e antecedente alla data
   # And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore data errata
    And Verifica che non è possibile selezionare una data Fine antecedente ad oggi
    And Nella sezione Le Tue Deleghe inserire una data
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    Then Si conferma l'azione scegliendo revoca la delega
    # And Logout da portale persona giuridica


  @TA_PGdeleganteAggiuntaDelegaAseStessi
  Scenario: [DELEGANTE PG AMMINISTRATORE] - Il delegante aggiunta delega a se stessi
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa      |
      | codiceFiscale  | 27957814470       |
      | ente           | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Convivio |
      | cognome | Spa      |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    Then Si conferma l'azione scegliendo revoca la delega