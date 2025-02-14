Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneCopiaCodiciPublicKeyAttiva_QA_5308
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5308 [DELEGANTE PG AMMINISTRATORE] - Visualizzazione e copia dei codici per un utente Amministratore PG con Public Key attiva
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API
    And Cliccare sui tre puntini con stato "Attiva"
    Then Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia
    And Da Visualizza codice si copia correttamente il campo KID cliccando sul bottone di copia
    And Da Visualizza codice si copia correttamente il campo Issuer cliccando sul bottone di copia
    And Nella pop up cliccare sul tasto chiudi
    And Logout da portale mittente