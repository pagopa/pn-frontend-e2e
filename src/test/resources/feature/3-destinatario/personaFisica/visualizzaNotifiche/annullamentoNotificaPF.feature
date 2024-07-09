Feature: Il mittente invia una notifica analogica o digitale a destinatario persona fisica e viene annullata

  @TA_visualizzaNotificaAnnullataPF
  @PF
  @Parallelism
Scenario: [TA-FE INVIO DI UNA NOTIFICA A PERSONA FISICA E ANNULLAMENTO] - Mittente invia una notifica e la annulla, anche la persona fisica vede la notifica annullata
  Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
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
    | nomeCognome       | Gaio Giulio       |
    | codiceFiscale     | CSRGGL44L13H501E        |
    | tipoDestinatario  | PF                |
    | domicilioDigitale | test@test.com      |
    | avvisoPagoPa      | 1                  |
    | F24               | 1                  |
  Then Creo in background una notifica per destinatario tramite API REST
  And Si seleziona la notifica
  And Si attende completamento notifica
  And Si annulla la notifica
  And Si visualizza correttamente la section Dettaglio Notifica annullata
  And Logout da portale mittente
  Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
  And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
  And Si seleziona la notifica destinatario
  And Si visualizza correttamente la section Dettaglio Notifica annullata