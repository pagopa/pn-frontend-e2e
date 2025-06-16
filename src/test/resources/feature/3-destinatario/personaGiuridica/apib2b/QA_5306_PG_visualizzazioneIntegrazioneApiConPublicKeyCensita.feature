Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneIntegrazioneAPIPublicKeyCensita_QA_5306
  @integrazioneApi_NRT
  #@bilinguismo
  @PG
  @TestSuite

  Scenario: PN-QA-5306 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG visualizza sezione Integrazione con Public Key censita
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
    # Esecuzione scenario
    When C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API
    Then Si verifica che la tabella delle chiavi pubbliche sia presente