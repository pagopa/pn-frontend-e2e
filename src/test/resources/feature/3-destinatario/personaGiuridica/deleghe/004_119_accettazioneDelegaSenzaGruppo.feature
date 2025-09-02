Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo personaGiuridica

  @TestSuite
  @TA_PGaccettazioneDelegaSenzaGruppo
  @DeleghePG
  @PG
  @DeleghePFPG1
  @NRT_Blocco_1
  @NRT_PN13213
  @deleghe2
  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
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
    And Si controlla la tabella deleghe a carico dell impresa
    And Si inserisce un codice della delega a carico dell impresa errato nella modale
    Then Le textbox che contengono le cifre del codice delega diventano rosse
    And Si accetta la delega senza gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Convivio"