Feature: Presenza del documento di attestazione opponibile a terzi relativo all'annullamento della notifica

@TestSuite
@PF
@TA_verificaDocumentoAnnullamentoNotifica_PA_PF
@NRT_Blocco_2

  Scenario: [ATTESTATO_ANNULLAMENTO_LEGAL_FACT_PA_PF] Mittente - Verifica che sia presente il documento di attestazione opponibile a terzi relativo all'annullamento della notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU ATTESTATO_ANNULLAMENTO_LEGAL_FACT_PA_PF |
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
    And Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica mittente
    And Si visualizza correttamente la sezione Dettaglio Notifica
    And Si annulla la notifica
    And Si controlla la comparsa del pop up di conferma annullamento
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Aspetta 120 secondi
    #When Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JRTR-TEDZ-MRVM-202504-J-1"
    And Si visualizza correttamente la section Dettaglio Notifica annullata
  #And Logout da portale mittente
    Then PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica annullata