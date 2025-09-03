Feature: La persona giuridica visualizza le deleghe

  @TestSuite
  @TA_PGVisualizzaDelegheSenzaGruppo
  @DeleghePG
  @PG
  @DeleghePFPG
  @NRT_Blocco_1
  @NRT_PN13213
  @deleghe2
  Scenario: PN-9166-A112 - La persona giuridica visualizza le deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    # Seconda PG non disponibile per TA, si crea una delega per persona fisica
    #And Creo in background una delega per persona giuridica
    #  | accessoCome    | delegante         |
    #  | fiscalCode     | LELPTR04A01C352E  |
    #  | companyName    | Le Epistolae srl  |
    #  | displayName    | Le Epistolae srl  |
    #  | person         | false             |
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Lucrezia Borgia"
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si controlla la tabella delegati dall impresa
    And Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si controlla la tabella deleghe a carico dell impresa
    And Si accetta la delega senza gruppo