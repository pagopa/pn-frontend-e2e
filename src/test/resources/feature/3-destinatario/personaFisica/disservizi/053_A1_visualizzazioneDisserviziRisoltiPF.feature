Feature: Il destinatario persona fisica visualizza i disservizi della piattaforma risolti

  @TestSuite
  @TA_PF_VisualizzaDisserviziRisolti
  # @NRT

  Scenario: PN-9433 - Il destinatario persona fisica visualizza i disservizi della piattaforma risolti
    Given Creazione disservizio new su portale helpdesk
    And Aspetta 3 secondi
    And Risoluzione disservizio new su portale helpdesk
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza correttamente la tabella dei disservizi
    And Si visualizzano tutti i record in elenco relativi a disservizi risolti