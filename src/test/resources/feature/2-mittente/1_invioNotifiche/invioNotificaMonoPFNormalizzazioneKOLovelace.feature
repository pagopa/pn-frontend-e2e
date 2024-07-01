Feature: Mittente genera una notifica che non prevede pagamento

  @TestSuite
  @TA_InvioNotificaMonoPFNormalizzazioneKOLovelace
  @mittente
  @invioNotifiche

  Scenario: PN-9294 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | @FAIL-Irreperibile_AR |
      | codicePostale    | 20147                 |
      | comune           | Milano                |
      | dettagliComune   | Milano                |
      | provincia        | MI                    |
      | stato            | Italia                |
      | nomeCognome      | Ada Lovelace          |
      | codiceFiscale    | LVLDAA85T50G702B      |
      | tipoDestinatario | PF                    |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 360 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | ada                    |
      | pwd          | password123            |
      | name         | Ada                    |
      | familyName   | Lovelace               |
      | fiscalNumber | TINIT-LVLDAA85T50G702B |
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

