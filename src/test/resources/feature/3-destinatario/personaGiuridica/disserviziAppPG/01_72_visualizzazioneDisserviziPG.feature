Feature: La persona giuridica visualizza i disservizi della applicazione

  @TestSuite
  @test72
  @TA_PG_VisualizzaDisservizio

  Scenario: PN-9163 - Il persona giuridica loggato visualizza lo stato dei disservizi
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella dashboard persona giuridica clicca su disservizi app
    And Si visualizza correttamente la Pagina dello Stato della piattaforma
    And Si visualizzano correttamente i dati sullo stato della piattaforma
    And Si visualizza storico disservizi
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk
    And Click su card monitoraggio piattaforma
    And Si visualizza correttamente home monitoraggio
    And Si crea il disservizio
    And Si verifica la creazione del disservizio
    And Si verifica avvenuto disservizio in pagina stato piattaforma
    And Annullamento disservizio

