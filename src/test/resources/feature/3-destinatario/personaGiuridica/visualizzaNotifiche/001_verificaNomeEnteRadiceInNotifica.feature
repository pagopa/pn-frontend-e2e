Feature: La persona fisica visualizza il nome concatenato dell'ente Radice nel campo mittente della notifica

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @verificaNomeEnteRadice
  @DeleghePG
  @PG

  Scenario: PN-10431 - La persona giuridica visualizza il campo mittente della notifica con il nome concatenato dell'ente Radice
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    Then Si verifica che il mittente sia "Comune di Verona"
    And Logout da portale persona giuridica