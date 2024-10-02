Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @Parallel
  @InvioNotificaADueDestinatariSoloUnoRaggiungibile

  Scenario: PN-9255 [TA-FE INVIO NOTIFICA A DUE DESTINATARI ENTRAMBI RAGGIUNGIBILI] - Il mittente invia una notifica a due destinatari , entrambi raggiungibili al primo tentavio
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                 |
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
      | domicilioDigitale | test@pec.com     |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    And Si aggiunge un destinatario alla notifica
      | tipoDestinatario  | PG           |
      | nomeCognome       | Convivio Spa |
      | codiceFiscale     | 27957814470  |
      | domicilioDigitale | prova@pec.it |
      | at                | Presso       |
      | indirizzo         | Via Roma 20  |
      | dettagliIndirizzo | Scala b      |
      | comune            | Milano       |
      | dettagliComune    | Milano       |
      | provincia         | MI           |
      | codicePostale     | 20147        |
      | stato             | Italia       |
      | avvisoPagoPa      | 1            |
      | F24               | 1            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Depositata"
    And Aspetta 60 secondi
    And Si visualizza correttamente la timeline relativi a tutti i destinatari
      | PF | CSRGGL44L13H501E |
      | PG | 27957814470      |
    Then In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline "Invio via PEC riuscito"
    And Aspetta 10 secondi
    Then In parallelo si effettua l'accesso al portale destinatario persona giuridica e si verifica la timeline "Invio via PEC riuscito"
    And Logout da portale mittente

