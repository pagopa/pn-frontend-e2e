Feature:Il delegato persona giuridica accetta la delega assegnandoli un gruppo

  @TestSuite
  @TA_PGaccettaDelegaConGruppo
  @DeleghePG
  @PG
  @DeleghePFPG
  @NRT_Blocco_1
  @NRT_PN13213
  @deleghe2
  Scenario: PN-9170-A116 - Il delegato persona giuridica accetta la delega assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Refresh pagina
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Convivio Spa"
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si accetta la delega con gruppo "Test gruppi"
    And Si controlla che la delega PG ha lo stato Attiva "Convivio"