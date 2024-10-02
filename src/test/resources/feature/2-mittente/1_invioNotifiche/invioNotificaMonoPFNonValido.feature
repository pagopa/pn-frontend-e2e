Feature: Mittente genera una notifica che non prevede pagamento

  @Parallel
  @TA_InvioNotificaPFNonValido
  @mittente
  @invioNotifiche

  Scenario: PN-9296 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                 |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome      | Franco Non Valido                 |
      | codiceFiscale    | NNVFNC80A01H501G                   |
      | tipoDestinatario | PF                                 |
      | indirizzo        | via @FAIL-DiscoveryIrreperibile_AR |
      | numeroCivico     | 20                                 |
      | comune           | MILANO                             |
      | provincia        | MI                                 |
      | codicepostale    | 20147                              |
      | stato            | ITALIA                             |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Depositata"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | franco                 |
      | pwd          | password123            |
      | name         | Franco                 |
      | familyName   | Non Valido             |
      | fiscalNumber | TINIT-NNVFNC80A01H501G |
    And Aspetta 200 secondi
    And Si seleziona la notifica destinatario
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

