Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

#  @TA_PG_RotazioneChiavePubblicaECreazioneChiavePubblicaConStessoValore_QA_5310
#  @integrazioneApi
#  @apiKey
#  #@bilinguismo
#  @PG
#  @TestSuite
#  @NRT_Blocco_2

  #  Inclobato nella 5305
  Scenario: PN-QA-5310 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG ruota una chiave pubblica attiva e censisce una chiave pubblica con il valore della chiave ruotata
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
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave precedentemente copiata dalla tabella delle chiavi pubbliche
      | nome | Chiave- |
    Then Verifica messaggio PublicKey di errore "Questo valore è già stato inserito in precedenza. Inserisci una chiave diversa."
    And Verifica tasto registra disabilitato
