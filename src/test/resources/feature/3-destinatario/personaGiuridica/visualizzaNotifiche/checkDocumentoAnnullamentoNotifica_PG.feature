Feature: Presenza del documento di attestazione opponibile a terzi relativo all'annullamento della notifica

  @TestSuite
  @PG
  @TA_verificaDocumentoAnnullamentoNotifica_PA_PG
  @NRT

  Scenario: [ATTESTATO_ANNULLAMENTO_LEGAL_FACT_PA_PG] Mittente - Verifica che sia presente il documento di attestazione opponibile a terzi relativo all'annullamento della notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | AR                 |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
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
      And Creo in background una notifica per destinatario tramite API REST
      And Si seleziona la notifica mittente
      And Si visualizza correttamente la sezione Dettaglio Notifica
      And Si annulla la notifica
      And Si controlla la comparsa del pop up di conferma annullamento
      And Si verifica che la notifica abbia lo stato "Annullata"
      And Aspetta 120 secondi
      And Si visualizza correttamente la section Dettaglio Notifica annullata
    #And Logout da portale mittente
      And PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
      And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
      And Si seleziona la notifica destinatario
      And Si visualizza correttamente la section Dettaglio Notifica annullata