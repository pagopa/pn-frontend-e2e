Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

  @TA_PG_CreazioneChiavePubblica_QA_5305_5306_5308_5310_5348
  @IntegrazioneApiDeleganteResources
    @apiKey
  #@bilinguismo
  @PG
  @TestSuite
  @NRT_Blocco_2

  Scenario: PN-QA-5305_5306_5308_5310_5348 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG censisce una chiave pubblica per la Persona Giuridica,
          visualizza sezione Integrazione con Public Key censita, Visualizzazione e copia dei codici per un utente Amministratore PG con Public Key attiva,
          ruota una chiave pubblica attiva e censisce una chiave pubblica con il valore della chiave ruotata,
          blocca una chiave pubblica attiva e censisce una chiave pubblica con il valore della chiave bloccata
    # Reset ambiente di test
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
#    And Pulisci ambiente virtual keys
    And Pulisci ambiente public keys
    # Esecuzione scenario
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Su Ottieni Parametri si copia correttamente il campo KID cliccando sul bottone di copia
    And Su Ottieni Parametri si copia correttamente il campo Issuer cliccando sul bottone di copia
    And Cliccare su registra
    Then Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica
    And Nella pagina Integrazione API si controlla che non sia presente il bottone Genera chiave pubblica
#  5306
    When C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API
    Then Si verifica che la tabella delle chiavi pubbliche sia presente
#  5308
    And Cliccare sui tre puntini con stato "Attiva"
    Then Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia
    And Da Visualizza codice si copia correttamente il campo KID cliccando sul bottone di copia
    And Da Visualizza codice si copia correttamente il campo Issuer cliccando sul bottone di copia
    And Nella pop up cliccare sul tasto chiudi
#  5310
    When Si copia il valore della chiave pubblica dalla tabella delle chiavi pubbliche
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave precedentemente copiata dalla tabella delle chiavi pubbliche
      | nome | Chiave- |
    Then Verifica messaggio PublicKey di errore "Questo valore è già stato inserito in precedenza. Inserisci una chiave diversa."
    And Verifica tasto registra disabilitato
#  5348
    And Torna indietro
    And Nella section cliccare sul tasto esci
    When Si copia il valore della chiave pubblica dalla tabella delle chiavi pubbliche
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave precedentemente copiata dalla tabella delle chiavi pubbliche
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    Then Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica
    And Nella pagina Integrazione API si controlla che non sia presente il bottone Genera chiave pubblica