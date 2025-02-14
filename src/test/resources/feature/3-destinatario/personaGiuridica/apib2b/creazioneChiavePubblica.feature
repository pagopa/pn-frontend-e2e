Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

  @TA_PG_CreazioneChiavePubblica_QA_5305
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5305 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG censisce una chiave pubblica per la Persona Giuridica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome        | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Su Ottieni Parametri si copia correttamente il campo KID cliccando sul bottone di copia
    And Su Ottieni Parametri si copia correttamente il campo Issuer cliccando sul bottone di copia
    And Cliccare su registra
    Then Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica
    And Si controlla che il pulsante Genera chiave pubblica non sia più presente nella pagina Integrazione API
    And Logout da portale persona giuridica

  @TA_PG_RotazioneChiavePubblicaECreazioneChiavePubblicaConStessoValore_QA_5310
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5310 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG ruota una chiave pubblica attiva e censisce una chiave pubblica con il valore della chiave ruotata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Verifica stato "Attiva"
    And Si copia il valore della chiave pubblica dalla tabella delle chiavi pubbliche
    And Cliccare sui tre puntini con stato "Attiva"
    And Si clicca Ruota
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave ruotata dalla tabella delle chiavi pubbliche
      | nome        | Chiave- |
    Then Verifica messaggio PublicKey di errore "Questo valore è già stato inserito in precedenza. Inserisci una chiave diversa."
    And Verifica tasto registra disabilitato
