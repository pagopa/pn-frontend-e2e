Feature: La persona giuridica visualizza i disservizi della applicazione

  @TestSuite
  @TA_PG_VisualizzaDisservizioRisolto
  @DisserviziAppPG
  @PG

  Scenario: PN-9163 - Il persona giuridica loggato visualizza lo stato dei disservizi
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella dashboard persona giuridica clicca su disservizi app
    And Si visualizza correttamente la Pagina dello Stato della piattaforma
    And Si visualizzano correttamente i dati sullo stato della piattaforma
    And Si visualizza storico disservizi
    And Si visualizza un record in elenco relativo ad un disservizio risolto
    And Si scarica attestazione opponibile, e si controlla che il download sia avvenuto
    And Logout da portale persona giuridica

