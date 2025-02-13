Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneIntegrazioneAPIPublicKeyCensita_QA_5306
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5306 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG visualizza sezione Integrazione con Public Key censita
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API
    Then Si verifica che la tabella delle chiavi pubbliche sia presente

  @TA_PG_VisualizzazioneCopiaCodiciPublicKeyAttiva_QA_5308
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5308 [DELEGANTE PG AMMINISTRATORE] - Visualizzazione e copia dei codici per un utente Amministratore PG con Public Key attiva
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Cliccare sui tre puntini con stato "Attiva"
    Then Si clicca visualizza codice e si copia il valore della chiave pubblica
    And Nella pop up cliccare sul tasto chiudi
    And Logout da portale mittente
