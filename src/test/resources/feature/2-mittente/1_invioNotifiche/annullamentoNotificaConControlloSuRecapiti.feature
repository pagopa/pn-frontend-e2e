Feature: Mittente invia una notifica analogica o digitale che viene annullata e che segue controlli recapiti


  @TA_annullamentoNotificaMittenteEControlloPEC
  @Parallelism
  @PA
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica a destinatario con PEC impostata e la annulla, si controlla che le ricevute PEC sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         | VIA ROMA 20      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Gaio Giulio      |
      | codiceFiscale     | CSRGGL44L13H501E |
      | tipoDestinatario  | PF               |
      | domicilioDigitale | test@test.com    |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Si attende completamento notifica
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 10 secondi
    And Si controlla che le ricevute PEC siano scaricabili
    And Logout da portale mittente
