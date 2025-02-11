Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneIntegrazioneAPIPublicKeyCensita_QA_5306
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5305 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG censisce una chiave pubblica per la Persona Giuridica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API
    Then Si verifica che la tabella delle chiavi pubbliche sia presente
