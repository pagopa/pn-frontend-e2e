Feature:La persona giuridica fa una ricerca per gruppo delle deleghe

  @TestSuite
  @TA_PGricercaDeleghePerGruppo
  @DeleghePG
  @PG
  @DeleghePFPG
  @NRT_Blocco_1
  @NRT_PN13213
  @deleghe2
  Scenario: [PN-9167] - La persona giuridica fa una ricerca per gruppo delle deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Lucrezia Borgia"
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Verona    |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "PF"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Si controlla la tabella delegati dall impresa
    And Nella sezione Delegati dall impresa si visualizza correttamente una delega in stato di attesa di conferma "Lucrezia Borgia" e si revoca
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Convivio Spa"
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    Then Nella sezione della nuova delega si sceglie la visualizzazione delle notifiche da parte di: "tutti gli enti"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Si controlla la tabella deleghe a carico dell impresa
    And Si accetta la delega con gruppo "Test gruppi"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il gruppo del delegante
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che ci sia una delega con la ragione sociale inserita "Convivio Spa"