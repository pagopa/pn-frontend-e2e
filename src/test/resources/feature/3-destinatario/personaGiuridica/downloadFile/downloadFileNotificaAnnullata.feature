Feature: Mittente invia una notifica digitale che viene annullata e lato destinatario si controlla non sia possibile scaricare alcun file

  @TA_checkDownloadFileNotificaAnnullataPG
  @PG
  @Parallelism

  Scenario: [TA-FE INVIO DI UNA NOTIFICA, ANNULLAMENTO, CHECK DOWNLOAD FILE PER PG] - Mittente invia una notifica e la annulla, il destinatario persona giuridica non può scaricare i file
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una "PEC" e si inserisce "prova@test.it"
    And Logout da portale persona giuridica
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
      | nomeCognome       | Convivio Spa       |
      | codiceFiscale     | 27957814470        |
      | tipoDestinatario  | PG                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Si attende completamento notifica
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 120 secondi
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Si verifica che gli allegati denominati "PAGAMENTO RATA IMU" non sono scaricabili
    And Si verifica che gli AAR non sono scaricabili
    And Si verifica che le attestazioni opponibili a terzi non siano scaricabili
    And Si verifica che non sia possibile scaricare le ricevute PEC
    And Si verifica la presenza del banner di avviso annullamento notifica
    And Logout da portale persona giuridica