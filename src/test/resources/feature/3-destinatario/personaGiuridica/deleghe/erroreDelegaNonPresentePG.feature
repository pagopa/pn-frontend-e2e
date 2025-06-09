Feature: Il delegato visualizza la notifiche del delegante
  
  @TestSuite
  @TA_PGErroreDelegaNonPresente
  @DeleghePG
  @PG
  @deleghe2
  @DeleghePFPG
  @GestioneErrori
  Scenario: [PN-14926-MANDATE_NOTFOUND_PG] - Errore per operazioni su una delega che non esiste
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Vita Nova Sas  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    And Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta
    And Nella sezione Aggiungi Delega persona giuridica salvare il codice verifica all'interno del file
    And Nella sezione Aggiungi Delega persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Delegati dall impresa si visualizza correttamente una delega in stato di attesa di conferma "Vita Nova Sas" e si revoca

    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone rifiuta delega
    And Verifica Messaggio toast di errore "Delega non trovata"
    And Refresh pagina
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Vita Nova Sas"