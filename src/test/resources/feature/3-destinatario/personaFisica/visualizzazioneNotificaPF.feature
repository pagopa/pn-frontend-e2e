Feature:Controllo dati notifica annullata

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  @CheckNotificaConDocumentiAllegatiPF1
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PF visualizza Notifica mono destinatario non ancora pagata solo con avviso PagoPa e assenza del PDF relativo al bollettino
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "YRUZ-NYXJ-DAJK-202405-N-1"
    And Cliccare sul bottone Filtra del delegato
    And Si clicca la notifica ricercata
    And Si controlla la presenza di codice avviso
    And Si controlla non sia presente l'avviso PagoPa
    And Logout da portale persona fisica