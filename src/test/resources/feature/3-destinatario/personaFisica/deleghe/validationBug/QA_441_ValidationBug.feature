Feature:La persona fisica aggiunge una nuova delega

  @TestSuite
  @TA_PF_QA_441
  @NRT_Blocco_1
  @DeleghePF
  @deleghe1
  Scenario: [QA_441] ValidationBug Visualizzato messaggio di errore portale PF
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega PF
    And Seleziona PG radio button portale "PF"
    And Inserire Codice Fiscale "1A2S4D5G7G8"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    Then Verifica messaggio errore Deleghe "Inserisci solo numeri. Se il Codice Fiscale della persona che vuoi delegare"


