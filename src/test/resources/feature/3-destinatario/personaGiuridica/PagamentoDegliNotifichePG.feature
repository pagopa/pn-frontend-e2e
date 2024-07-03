Feature: PG visualizza il dettaglio di una notifica con documenti allegati

  @TestSuite
  @TA_PGDettaglioNotificheConDocumenti
  @PG
  @visualizzazioneNotifichePG

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica mono destinatario con più avvisi PagoPa - Pagamento di un avviso PagoPa
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
      | avvisoPagoPa     | 2            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica mono destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
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
      | avvisoPagoPa     | 2            |
      | F24              | 1            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario - Pagamento di un solo avviso PagoPa
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
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa PG
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica


  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario con più avvisi PagoPa - Pagamento di un solo avviso PagoPa
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
      | avvisoPagoPa     | 2            |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica

  @PagaNotificaConDocumentiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PG NON PAGATA] - PG  visualizza Notifica multi destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
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
      | avvisoPagoPa     | 2            |
      | F24              | 1            |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    And Si clicca sul radio bottone di pagamento
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica
