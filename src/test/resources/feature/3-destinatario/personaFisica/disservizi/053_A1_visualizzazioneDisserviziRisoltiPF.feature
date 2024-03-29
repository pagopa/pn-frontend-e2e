Feature: Il destinatario persona fisica visualizza i disservizi della piattaforma risolti

  @TestSuite
  @PF
  @disserviziPF
  @TA_PF_VisualizzaDisserviziRisolti

  Scenario: PN-9433 - Il destinatario persona fisica visualizza i disservizi della piattaforma risolti
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk
    And Click su card monitoraggio piattaforma
    And Si visualizza correttamente home monitoraggio
    And Si annulla un disservizio in corso
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza correttamente la tabella dei disservizi
    And Si visualizzano tutti i record in elenco relativi a disservizi risolti
    And Logout da portale persona fisica