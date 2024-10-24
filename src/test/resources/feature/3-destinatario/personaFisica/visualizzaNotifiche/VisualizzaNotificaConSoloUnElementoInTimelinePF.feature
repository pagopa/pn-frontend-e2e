Feature: PF visualizza il dettaglio di una notifica con solo un elemento in timeline

  @Paralell
  @TA_PFDettaglioNotificheConUnElementoInTimeline
  @PF
  @visualizzazioneNotifichePF

  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimelinePF
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF] - PF  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_AR_ZIP
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @OK_AR_ZIP       |
      | codicePostale    | 20147            |
      | comune           | Milano           |
      | dettagliComune   | Milano           |
      | provincia        | MI               |
      | stato            | Italia           |
      | nomeCognome      | Giulio Cesare     |
      | codiceFiscale    | CSRGGL44L13H501E |
      | tipoDestinatario | PF               |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si attende completamento notifica "Consegnata"
    And Si visualizza correttamente la section Dettaglio Notifica
    And controllo link per scaricare zip e scarico file Ricevuta di consegna
    And Aspetta 5 secondi
    And estraggo il file zip
    And Controllo sia presente documento pdf
    And Si elimina file estratto
    And Logout da portale persona fisica


  @VisualizzaNotificaMonoDestinatarioConUnElementoInTimeline890PF
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF] - PF  Verifica presenza solo un elemento in timeline contenente la ricevuta di postalizzazione in formato zip - OK_AR_ZIP
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @OK_890_ZIP      |
      | codicePostale    | 20147            |
      | comune           | Milano           |
      | dettagliComune   | Milano           |
      | provincia        | MI               |
      | stato            | Italia           |
      | nomeCognome      | Giulio Cesare     |
      | codiceFiscale    | CSRGGL44L13H501E |
      | tipoDestinatario | PF               |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si attende completamento notifica "Consegnata"
    And Si visualizza correttamente la section Dettaglio Notifica
    And controllo link per scaricare zip e scarico file Ricevuta di consegna
    And Aspetta 5 secondi
    And estraggo il file zip
    And Controllo sia presente documento pdf
    And Si elimina file estratto
    And Logout da portale persona fisica
