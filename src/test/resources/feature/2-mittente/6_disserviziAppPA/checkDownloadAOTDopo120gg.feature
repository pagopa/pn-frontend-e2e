Feature: Mittente prova ad effettuare download AOT scaduti da 120gg

  @TestSuite
  @mittente
  @TA_MittenteVisualizzazioneDisservizi
  @TA_PA_DownloadDisserviziScadutiDa120gg
  @120gg
  Scenario: [TA-FE MITTENTE EFFETTUA DOWNLOAD DI AOT SCADUTO DA 120 GG] - mittente effettua download di aot scaduto da 120gg
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    Then Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente
    And Si visualizza correttamente la tabella dei disservizi
    And Nella pagina stato della piattaforma si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta fino all'ultima
    And Download file attestazione disservizio
    And Si controlla che esista pop up scadenza
    And Logout da portale mittente

