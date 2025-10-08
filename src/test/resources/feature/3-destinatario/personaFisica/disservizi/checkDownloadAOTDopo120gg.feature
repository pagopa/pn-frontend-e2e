Feature: Persona Fisica prova ad effettuare download AOT scaduti da 120gg

  @PF
  @TA_PF_DownloadDisserviziScadutiDa120gg
  @NRT_Blocco_2
  Scenario: [TA-FE PF EFFETTUA DOWNLOAD DI AOT SCADUTO DA 120 GG] - pf effettua download di aot scaduto da 120gg
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza correttamente la tabella dei disservizi
    And Nella pagina stato della piattaforma si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina stato della piattaforma si cambia pagina fino a 550 giorni indietro
    And Attesa 4 secondi
    And Download file attestazione disservizio

