Feature: PG visualizza il dettaglio di una notifica con documenti allegati

  @TestSuite
  @TA_PGDettaglioNotificheConDocumenti
  @PG
  @visualizzazioneNotifichePG

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica mono destinatario con più avvisi PagoPa - Pagamento di un avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "JRNE-EZAY-DXGZ-202405-R-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    # questo step da 9436 And Si clicca il buttone paga
     # questo step da 9436 fare checkout
    # questo step da 9436 Then L'avviso e stato pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica mono destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "YTHU-HQYM-GTYA-202406-P-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    # questo step da 9436 And Si clicca il buttone paga
     # questo step da 9436 fare checkout
    # questo step da 9436 Then L'avviso e stato pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario - Pagamento di un solo avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "ZVTW-NJPU-XDYM-202406-Q-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa PG
    # questo step da 9436 And Si clicca il buttone paga
     # questo step da 9436 fare checkout
    # questo step da 9436 Then L'avviso e stato pagato
    And Logout da portale persona giuridica


  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario - Pagamento di un solo avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "JRNE-EZAY-DXGZ-202405-R-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    # questo step da 9436 And Si clicca il buttone paga
     # questo step da 9436 fare checkout
    # questo step da 9436 Then L'avviso e stato pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "YTHU-HQYM-GTYA-202406-P-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    # questo step da 9436 And Si clicca il buttone paga
     # questo step da 9436 fare checkout
    # questo step da 9436 Then L'avviso e stato pagato
    And Logout da portale persona giuridica
