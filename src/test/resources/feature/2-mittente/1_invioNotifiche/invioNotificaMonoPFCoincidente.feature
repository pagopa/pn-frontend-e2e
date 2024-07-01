Feature: Mittente genera una notifica che non prevede pagamento

  @TestSuite
  @TA_InvioNotificaPFCoincidente
  @mittente
  @invioNotifiche

  Scenario: PN-9295 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo         | @FAIL-Irreperibile_AR |
      | codicePostale     | 40121                 |
      | comune            | Bologna               |
      | dettagliComune    | Bologna               |
      | provincia         | BO                    |
      | stato             | Italia                |
      | nomeCognome       | Giuseppe Coincidente  |
      | codiceFiscale     | CNCGPP80A01H501J      |
      | tipoDestinatario  | PF                    |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 310 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | giuseppe               |
      | pwd          | password123            |
      | name         | Giuseppe               |
      | familyName   | Coincidente            |
      | fiscalNumber | TINIT-CNCGPP80A01H501J |
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

