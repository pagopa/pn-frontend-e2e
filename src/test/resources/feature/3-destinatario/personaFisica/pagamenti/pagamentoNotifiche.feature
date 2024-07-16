Feature:Pagamento notifica

  @TestSuite
  @PF
  @PagamentoNotificaPF

  Scenario:[NOTIFICA-PAGAMENTO NOTIFICA AVVISO PAGOPA] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso             |
      | indirizzo         | VIA ROMA 20        |
      | dettagliIndirizzo | Scala b            |
      | codicePostale     | 20147              |
      | comune            | Milano             |
      | dettagliComune    | Milano             |
      | provincia         | MI                 |
      | stato             | Italia             |
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Si seleziona la notifica

  @PagamentoNotificaMonoPFConPiuAvvisiEF24
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF NON PAGATA] - PF  visualizza Notifica mono destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
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
      | avvisoPagoPa     | 2                  |
      | F24              | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona fisica


  @PagamentoNotificaMonoPFConPiuAvvisi
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF NON PAGATA] - PF  visualizza Notifica mono destinatario con più avvisi PagoPa - Pagamento di un avviso PagoPa
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
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
      | avvisoPagoPa     | 2                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona fisica

  @PagamentoNotificaMultiPFConPiuAvvisiEF24
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF NON PAGATA] - PF  visualizza Notifica multi destinatario con più avvisi PagoPa e modello F24  - Pagamento di un avviso PagoPa
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
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
      | avvisoPagoPa     | 2                  |
      | F24              | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente il modello F24 PG
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona fisica


  @PagamentoNotificaMultiPFConPiuAvvisi
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA PF NON PAGATA] - PF  visualizza Notifica multi destinatario con più avvisi PagoPa - Pagamento di un solo avviso PagoPa
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
      | avvisoPagoPa     | 2                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 3 secondi
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente piu avvisi PagoPa PG
    And Si clicca sul radio bottone di pagamento
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica
