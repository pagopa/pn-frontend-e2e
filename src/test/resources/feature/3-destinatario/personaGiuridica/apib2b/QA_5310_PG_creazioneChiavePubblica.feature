Feature: Utente Amministratore Persona Giuridica censisce una chiave pubblica per la Persona Giuridica

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
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave ruotata dalla tabella delle chiavi pubbliche
      | nome | Chiave- |
    Then Verifica messaggio PublicKey di errore "Questo valore è già stato inserito in precedenza. Inserisci una chiave diversa."
    And Verifica tasto registra disabilitato
