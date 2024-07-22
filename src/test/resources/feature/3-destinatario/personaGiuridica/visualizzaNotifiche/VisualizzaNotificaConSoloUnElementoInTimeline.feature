Feature: PG visualizza il dettaglio di una notifica con solo un elemento in timeline

  @TestSuite
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
    And Si verifica che la notifica abbia lo stato "Consegnata"
    And controllo link per scaricare zip e scarico file Ricevuta di consegna
    And Aspetta 5 secondi
    And estraggo il file zip
    And Controllo sia presente documento pdf
    And Si elimina file estratto
    And Logout da portale persona giuridica

  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimeline890
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG] - PG  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_AR_ZIP
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
    And Si verifica che la notifica abbia lo stato "Consegnata"
    And controllo link per scaricare zip e scarico file Ricevuta di consegna
    And Aspetta 5 secondi
    And estraggo il file zip
    And Controllo sia presente documento pdf
    And Si elimina file estratto
    And Logout da portale persona giuridica