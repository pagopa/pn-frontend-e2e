Feature: PG visualizza il dettaglio di una notifica con documenti allegati

  @TestSuite
  @TA_PGDettaglioNotificheConDocumenti
  @PG
  @visualizzazioneNotifichePG

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG scarica modello F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "JRNE-EZAY-DXGZ-202405-R-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa PG
    And Si controlla sia presente il modello F24 PG
    And Si clicca il modello F24 PG
    Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG scarica l'avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "LDTY-YKMT-NVTD-202405-L-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa PG
    And Si clicca l'avviso PagoPa PG
    And Aspetta 3 secondi
    Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza dettaglio notifica con attestazione opponibile a terzi notifica presa in carico
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "LEHT-MGMP-HUQN-202405-Y-1"
    And Si clicca la notifica ricercata
    And Si controlla sia presente il modello F24 PG
    Then Si controlla sia presente attestazione opponibile a terzi notifica presa in carico
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizzazione notifica da pagare annullata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "ADHM-RLUJ-MVGQ-202405-T-1"
    And Si clicca la notifica ricercata
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Si controlla non sia presente l'avviso PagoPa PG
    Then Si controlla non sia presente il modello F24 PG
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza Notifica mono destinatario non ancora pagata solo con più modelli F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "XJRT-KHQU-UGEJ-202405-Q-1"
    And Si clicca la notifica ricercata
    And Si controlla sia visualizza box allegati modelli F24 PG
    And Si clicca il modello F24 PG
    And Aspetta 3 secondi
    #Questi step sono commentati perche su aws quando clicca su link, il file viene scaricato, ma in locale si apre in nuova scheda
    #And Si torna alla pagina precedente
    And Si clicca il secondo modello F24 PG
    #Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza Notifica mono destinatario non ancora pagata solo con avviso PagoPa e assenza del PDF relativo al bollettino
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "NGQY-TQWG-YPRT-202405-E-1"
    And Si clicca la notifica ricercata
    And Si controlla la presenza di codice avviso
    And Si controlla non sia presente l'avviso PagoPa PG
    And Logout da portale persona giuridica