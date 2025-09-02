Feature:Il delegato persona giuridica annulLa l'operazione di rifiuto delega

  @TestSuite
  @TA_PGannullaRifiutoDelega
  @DeleghePG
  @PG
  @DeleghePGPF
  @DeleghePFPG
  @NRT_Blocco_1
  Scenario: PN-9172-A118 - Il delegato persona giuridica annulla l'operazione di rifiuto delega
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
    And Si sceglie opzione accetta
    And Si inserisce il codice della delega a carico dell impresa nella modale
    And Si clicca sul bottone accetta delega dopo aver inserito il codice di verifica
    And Si clicca su conferma in assegnazione gruppo
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone annulla
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
