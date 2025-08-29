Feature: La persona giuridica aggiunge una nuova delega

  @TestSuite
  @TA_PG_QA_464
  @NRT_Blocco_1
  @DeleghePG
  Scenario: [QA_464] ValidationBug Visualizzato messaggio di errore portale PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
#    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Seleziona PG radio button portale "PG"
    And Inserire Codice Fiscale "1A2S4D5G7G8"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    Then Verifica messaggio errore Deleghe "Inserisci solo numeri. Se il Codice Fiscale della persona che vuoi delegare"