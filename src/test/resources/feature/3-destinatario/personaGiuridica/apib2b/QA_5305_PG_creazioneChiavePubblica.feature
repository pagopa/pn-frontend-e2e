Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

  @TA_PG_CreazioneChiavePubblica_QA_5305
  @integrazioneApi
  @apiB2BBilinguismo
  @PG
  @TestSuite

  Scenario: PN-QA-5305 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG censisce una chiave pubblica per la Persona Giuridica
    # Reset ambiente di test
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
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
    And Logout da portale persona giuridica delegante