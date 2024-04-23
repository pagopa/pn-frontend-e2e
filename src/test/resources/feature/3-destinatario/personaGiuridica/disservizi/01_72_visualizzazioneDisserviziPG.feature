Feature: La persona giuridica visualizza i disservizi della applicazione

  @TestSuite
  @TA_PG_VisualizzaDisservizio
  @DisserviziAppPG
  @PG

  Scenario: PN-9163 - Il persona giuridica loggato visualizza lo stato dei disservizi
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella dashboard persona giuridica clicca su disservizi app
    And Si visualizza correttamente la Pagina dello Stato della piattaforma
    And Si visualizzano correttamente i dati sullo stato della piattaforma
    And Si visualizza storico disservizi
    Given Login helpdesk in nuova scheda
      | utente   | admin@test.pagopa.it |
      | password | Admin-testcognito1   |
    And Si visualizza correttamente home Helpdesk
    And Click su card monitoraggio piattaforma
    And Si visualizza correttamente home monitoraggio
    And Si crea il disservizio
    And Si verifica la creazione del disservizio
    And Si verifica avvenuto disservizio in pagina stato piattaforma
    And Si visualizza un record in elenco relativo ad un disservizio ancora in corso
    And Si annulla un disservizio in corso
    And Logout da portale persona giuridica

