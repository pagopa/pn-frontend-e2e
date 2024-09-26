Feature: La persona fisica visualizza il nome concatenato dell'ente Radice nel campo mittente della notifica

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @verificaNomeEnteRadice
  @DeleghePG
  @PG

  Scenario: PN-10431 - La persona giuridica visualizza il campo mittente della notifica con il nome concatenato dell'ente Radice
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA     |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
      | avvisoPagoPa     | 1            |
      | F24              | 1            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 60 secondi
    And Si visualizza correttamente la section Dettaglio Notifica
    Then Si verifica che il mittente sia "Comune di Palermo"
    And Logout da portale persona giuridica