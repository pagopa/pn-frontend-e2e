Feature:Deleghe lato ruolo operatore

  @TestSuite
  @DeleghePG
  @PG

  @TA_PGRuoloOperatoreControlloAssenzaDeleghe
  Scenario: [TA-FE CONTROLLO DELGHE LAYOUT RUOLO OPERATORE]- Si controlla lato ruolo operatore assenza deleghe
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | Convivio Spa   |
    And Si controlla che non esista il bottone deleghe nel side menu
    And Logout da portale persona giuridica

  @TA_PGRuoloOperatoreControlloAnnullamentoNotifica
  Scenario: [TA-FE CONTROLLO DELGHE LAYOUT RUOLO OPERATORE]- Si controlla lato ruolo operatore che non sia possibile annullare la notifica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | Convivio Spa   |
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
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 20 secondi
    And Si seleziona la notifica
    And Logout da portale persona giuridica