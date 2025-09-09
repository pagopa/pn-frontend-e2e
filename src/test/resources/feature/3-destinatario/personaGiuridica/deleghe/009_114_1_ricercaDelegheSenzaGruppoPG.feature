Feature:La persona giuridica visualizza le deleghe

  @TestSuite
  @TA_PGricercaDelegheSenzaGruppo
  @DeleghePG
  @PG
  @DeleghePFPG
  @NRT_Blocco_1
  Scenario: PN-9166-B112 - La persona giuridica fa una ricerca delle deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
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
    And Si accetta la delega senza gruppo
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante "27957814470"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito "Convivio Spa"