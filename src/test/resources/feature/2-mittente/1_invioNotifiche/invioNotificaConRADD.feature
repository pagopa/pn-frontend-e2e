Feature: Mittente invia una notifica analogica con controllo RADD

  @Parallel
  @invioNotificaConCheckRADD
  @PA

  Scenario: [TA-FE INVIO DI UNA NOTIFICA E CHECK RADD] - Mittente invia una notifica analogica e verifica alert RADD
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Si completa percorso RADD
    #When Si inizializzano i dati per la notifica
    #  | modello         | AR                |
    #  | documenti       | 1                  |
     # | oggettoNotifica | Pagamento rata IMU |
      #| costiNotifica   | false              |
    #And Si aggiunge un destinatario alla notifica
    #  | at                | Presso           |
    #  | indirizzo         | VIA ROMA 20      |
    #  | dettagliIndirizzo | Scala b          |
    #  | codicePostale     | 70123           |
    #  | comune            | BARI           |
    #  | dettagliComune    | BARI           |
    #  | provincia         | BA               |
    #  | stato             | Italia           |
    #  | nomeCognome       | Gaio Giulio      |
    #  | codiceFiscale     | CSRGGL44L13H501E |
    #  | tipoDestinatario  | PF               |
    #Then Creo in background una notifica per destinatario tramite API REST
    #And Si seleziona la notifica mittente
    And Logout da portale mittente