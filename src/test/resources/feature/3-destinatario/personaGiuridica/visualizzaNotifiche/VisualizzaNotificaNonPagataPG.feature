Feature: PG visualizza il dettaglio di una notifica con documenti allegati

  @Parallel
  @TA_PGDettaglioNotificheConDocumenti
  @PG
  @visualizzazioneNotifichePG

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG scarica modello F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA     |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
      | avvisoPagoPa     | 1            |
      | F24              | 2            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 60 secondi
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa destinatario
    And Si controlla sia presente il modello F24 destinatario
    And Si clicca sul modello F24 destinatario numero 1
    Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @TestSuite
  @CheckNotificaConDocumentiAllegatiPGBis
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - bis - PG scarica modello F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "WZAT-VPJY-XAZL-202410-A-1"
    And Cliccare sul bottone Filtra persona giuridica
    When La persona giuridica clicca sulla notifica restituita "WZAT-VPJY-XAZL-202410-A-1"
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa destinatario
    And Si controlla sia presente il modello F24 destinatario
    And Si clicca sul modello F24 destinatario numero 1
    Then Si torna alla pagina precedente
    And Logout da portale persona giuridica


  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG scarica l'avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA     |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
      | avvisoPagoPa     | 1            |
      | F24              | 1            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 60 secondi
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa destinatario
    And Si clicca l'avviso PagoPa destinatario
    And Aspetta 3 secondi
    Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza dettaglio notifica con attestazione opponibile a terzi notifica presa in carico
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "LEHT-MGMP-HUQN-202405-Y-1"
    And Si clicca la notifica ricercata
    And Si controlla sia presente il modello F24 destinatario
    Then Si controlla sia presente attestazione opponibile a terzi notifica presa in carico
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizzazione notifica da pagare annullata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "ADHM-RLUJ-MVGQ-202405-T-1"
    And Si clicca la notifica ricercata
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Si controlla non sia presente l'avviso PagoPa
    Then Si controlla non sia presente il modello F24 destinatario
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza Notifica mono destinatario non ancora pagata solo con più modelli F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "XJRT-KHQU-UGEJ-202405-Q-1"
    And Si clicca la notifica ricercata
    And Si controlla sia visualizza box allegati modelli F24 PG
    And Si clicca sul modello F24 destinatario numero 1
    And Aspetta 3 secondi
    #Questi step sono commentati perche su aws quando clicca su link, il file viene scaricato, ma in locale si apre in nuova scheda
    #And Si torna alla pagina precedente
    And Si clicca sul modello F24 destinatario numero 2
    #Then Si torna alla pagina precedente
    And Logout da portale persona giuridica

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG visualizza Notifica mono destinatario non ancora pagata solo con avviso PagoPa e assenza del PDF relativo al bollettino
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "NGQY-TQWG-YPRT-202405-E-1"
    And Si clicca la notifica ricercata
    And Si controlla la presenza di codice avviso
    And Si controlla non sia presente l'avviso PagoPa
    And Logout da portale persona giuridica