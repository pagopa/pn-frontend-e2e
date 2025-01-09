Feature: Persona Fisica prova ad effettuare download AOT scaduti da 120gg

  @TestSuite
  @PF
  @TA_PF_DownloadDisserviziScadutiDa120gg
  @120gg
  Scenario: [TA-FE PF EFFETTUA DOWNLOAD DI AOT SCADUTO DA 120 GG] - pf effettua download di aot scaduto da 120gg
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza correttamente la tabella dei disservizi
    And Nella pagina stato della piattaforma si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta fino all'ultima
    And Download file attestazione disservizio 2
    And Si controlla che esista pop up scadenza
    And Logout da portale persona fisica

