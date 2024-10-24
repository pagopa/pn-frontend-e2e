Feature: Mittente invia una notifica analogica o digitale che viene annullata

  @Parallel
  @annullamentoNotificaMittenteAvvenutoAccesso
  @PA

  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato avvenuto accesso viene annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
    Then Creo in background una notifica per destinatario tramite API REST
    Then In parallelo si effettua l'accesso al portale destinatario "persona fisica" e si apre la notifica ricevuta
    And Si seleziona la notifica mittente
    And Si attende che lo stato della notifica sia "Avvenuto accesso"
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente