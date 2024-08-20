Feature: Mittente genera una notifica che non prevede pagamento

  @Paralell
  @TA_InvioNotificaPFCoincidente
  @mittente
  @invioNotifiche

  Scenario: PN-9295 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | giuseppe               |
      | pwd          | password123            |
      | name         | Giuseppe               |
      | familyName   | Coincidente            |
      | fiscalNumber | TINIT-CNCGPP80A01H501J |
    When Si inizializzano i dati per la notifica
      | modello         | AR                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo         | via @FAIL-DiscoveryIrreperibile_AR  |
      | numeroCivico      | 20                                 |
      | codicePostale     | 40121              |
      | comune            | Bologna             |
      | provincia         | BO                 |
      | stato             | ITALIA             |
      | nomeCognome       | Giuseppe Coincidente |
      | codiceFiscale     | CNCGPP80A01H501J   |
      | tipoDestinatario  | PF                 |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 400 secondi
    And Si seleziona la notifica destinatario
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //div[@id='Destinatario irreperibile-status'] |
      | vediDettagli | false                                         |
    And Logout da portale persona fisica