Feature: Il delegato persona giuridica modifica una delega assegnandoli un gruppo

  @TestSuite
  @TA_PGmodificaDelegaConGruppo
  @DeleghePG
  @PG
  @DeleghePFPG
  @DeleghePFPG1
  @NRT_Blocco_1
  Scenario: PN-9173 - Il delegato persona giuridica modifica una delega assegnandoli un gruppo
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
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone assegna a un gruppo
    And Si selezione il gruppo della delega
    And Si clicca su conferma in assegnazione gruppo
    And Si controlla che la delega ha cambiato gruppo