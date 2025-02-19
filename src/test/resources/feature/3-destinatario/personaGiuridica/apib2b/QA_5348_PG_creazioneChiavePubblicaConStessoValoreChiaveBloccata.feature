Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

  @TA_PG_BloccoChiavePubblicaECreazioneChiavePubblicaConStessoValore_QA_5348
  @integrazioneApi
  @PG
  @TestSuite

  Scenario: QA-5348 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG blocca una chiave pubblica attiva e censisce una chiave pubblica con il valore della chiave bloccata
    # Reset ambiente di test
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    # Creazione chiave per scenario
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome        | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Verifica stato "Attiva"
    # Esecuzione scenario
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
    And Logout da portale persona giuridica delegante
