Feature: Persona Giuridica prova ad effettuare download AOT scaduti da 120gg

  @TA_PG_DownloadDisserviziScadutiDa120gg
  @NRT_Blocco_3
  @Disservizi
  Scenario: [TA-FE PG EFFETTUA DOWNLOAD DI AOT SCADUTO DA 120 GG] - PG effettua download di aot scaduto da 120gg
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza correttamente la tabella dei disservizi
    And Nella pagina stato della piattaforma si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina stato della piattaforma si cambia pagina fino a 550 giorni indietro
    And Attesa 2 secondi
    And Download file attestazione disservizio

