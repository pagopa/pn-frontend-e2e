Feature: Mittente visualizza il dettaglio di una notifica con documenti allegati

  @TestSuite
  @TA_MittenteDettaglioNotificheConDocumenti
  @mittente
  @visualizzazioneNotificheMittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza box allegati modelli F24
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "ETYM-TMVQ-RKGP-202409-M-1"
    And Si clicca la notifica ricercata
    And Si clicca sul bottone vedi tutti
    And Si controlla sia visualizza box allegati modelli F24
    Then Si clicca sul bottone chiudi box F24
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza dettaglio notifica con attestazione opponibile a terzi notifica presa in carico
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JHME-HQME-DGEL-202405-E-1"
    And Si clicca la notifica ricercata
    And Si controlla sia presente il modello F24
    Then Si controlla sia presente attestazione opponibile a terzi notifica presa in carico
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente scarica l'avviso PagoPa
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JHME-HQME-DGEL-202405-E-1"
    And Si clicca la notifica ricercata
    And Si controlla sia presente l'avviso PagoPa
    And Si clicca l'avviso PagoPa
    And Aspetta 3 secondi
    Then Si torna alla pagina precedente
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente scarica modello F24
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JHME-HQME-DGEL-202405-E-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il modello F24
    Then Si verifica che che non sia possibile effettuare il download del modelo F24
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza box di pagamento su notifica multi destinatario non ancora pagata solo con avviso PagoPa e modello F24 e costi di notifica non inclusi
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "TNPU-WLHJ-NVLZ-202405-T-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Si seleziona un destinatario
    And Si controlla sia presente l'avviso PagoPa
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza box di pagamento su notifica multi destinatario non ancora pagata solo con avviso PagoPa e modello F24 e costi di notifica inclusi
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "RHTG-TGXM-PVAX-202405-A-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Si seleziona un destinatario
    And Si controlla sia presente l'avviso PagoPa
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza box di pagamento su notifica multi destinatario non ancora pagata solo con modello F24 e costi di notifica non inclusi
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "QWZV-LDXY-NGTG-202405-X-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Si seleziona un destinatario
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza box di pagamento su notifica multi destinatario non ancora pagata solo con avviso PagoPa e costi di notifica non inclusi
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "TJYG-JZKE-ZGQR-202405-D-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Si seleziona un destinatario
    Then Si controlla sia presente l'avviso PagoPa
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegati
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - Mittente visualizza Notifica mono destinatario non ancora pagata solo con avviso PagoPa e assenza del PDF relativo al bollettino
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "DWNU-VYMK-DEJE-202408-J-1"
    And Si clicca la notifica ricercata
    And Si controlla la presenza di codice avviso mittente
    And Si controlla non sia presente l'avviso PagoPa mittente
    And Logout da portale mittente