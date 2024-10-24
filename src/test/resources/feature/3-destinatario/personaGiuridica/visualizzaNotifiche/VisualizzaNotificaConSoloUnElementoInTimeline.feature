Feature: PG visualizza il dettaglio di una notifica con solo un elemento in timeline

  @Parallel
  @TA_PGDettaglioNotificheConUnElementoInTimeline
  @PG
  @visualizzazioneNotifichePG

  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimelineAR
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG] - PG  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_AR_ZIP
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @OK_AR_ZIP   |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 60 secondi
    And Si visualizza correttamente la section Dettaglio Notifica
    And controllo Ricevuta di consegna link cliccabile
    And Logout da portale persona giuridica

  @TestSuite
  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimelineARBis
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG] - bis - PG  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_AR_ZIP
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "QPTV-HLXM-LGJE-202410-G-1"
    And Cliccare sul bottone Filtra persona giuridica
    When La persona giuridica clicca sulla notifica restituita "QPTV-HLXM-LGJE-202410-G-1"
    And Si visualizza correttamente la section Dettaglio Notifica
    And controllo Ricevuta di consegna link cliccabile
    And Logout da portale persona giuridica


  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimeline890
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG] - PG  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_890_ZIP
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @OK_890_ZIP   |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 60 secondi
    And Si visualizza correttamente la section Dettaglio Notifica
    And controllo Ricevuta di consegna link cliccabile
    And Logout da portale persona giuridica