Feature: La persona giuridica visualizza i disservizi della applicazione

  @TA_PG_VisualizzaDisservizioRisolto
  @bilinguismo
  @helpDesk
  @NRT_Blocco_3
  @Disservizi

  Scenario: PN-9164 - Il persona giuridica loggato visualizza lo stato dei disservizi

    Given Creazione disservizio new su portale helpdesk
    And Aspetta 3 secondi
    And Risoluzione disservizio new su portale helpdesk

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella dashboard persona giuridica clicca su disservizi app
    And Si visualizza correttamente la Pagina dello Stato della piattaforma
    And Si visualizzano correttamente i dati sullo stato della piattaforma
    And Si visualizza storico disservizi
    And Si visualizza un record in elenco relativo ad un disservizio risolto "Invio delle notifiche"
    And Si scarica attestazione opponibile, e si controlla che il download sia avvenuto

