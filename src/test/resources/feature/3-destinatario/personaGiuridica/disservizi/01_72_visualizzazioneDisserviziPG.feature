Feature: La persona giuridica visualizza i disservizi della applicazione

  @TestSuite
  @TA_PG_VisualizzaDisservizio
  @bilinguismo
  @helpDesk
  @NRT_Blocco_3_GRUPPO_AWS
  @Disservizi

  Scenario: PN-9163 - Il persona giuridica loggato visualizza lo stato dei disservizi

    Given Creazione disservizio new su portale helpdesk

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella dashboard persona giuridica clicca su disservizi app
    And Si visualizza correttamente la Pagina dello Stato della piattaforma
    And Si visualizzano correttamente i dati sullo stato della piattaforma
    And Si visualizza storico disservizi
    And Si visualizza un record in elenco relativo ad un disservizio ancora in corso
    And Risoluzione disservizio new su portale helpdesk