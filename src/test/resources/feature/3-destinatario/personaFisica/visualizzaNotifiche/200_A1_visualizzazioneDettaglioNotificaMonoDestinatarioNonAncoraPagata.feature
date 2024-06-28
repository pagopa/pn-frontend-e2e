Feature:La persona fisica visualizza le notifiche in elenco

  @TestSuite
  @TA_PFVisualizzazioneNotificaNonAncoraPagata
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9436 - Visualizzazione dettaglio di una notifica mono destinatario non ancora pagata, con successivo pagamento dell’avviso.
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso             |
      | indirizzo         | VIA ROMA 20        |
      | dettagliIndirizzo | Scala b            |
      | codicePostale     | 20147              |
      | comune            | Milano             |
      | dettagliComune    | Milano             |
      | provincia         | MI                 |
      | stato             | Italia             |
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 10 secondi
    And Si visualizzano le notifiche dalla piu recente
    And Cliccare sulla notifica restituita
    And Si controlla se la sezione pagamento visualizzata correttamente
    And Si controlla che costi di notifica inclusi non presente
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona fisica
