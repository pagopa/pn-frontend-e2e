Feature: annullamento della notifica

  @annullamentoNotificaPF
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica con avviso PagoPa e F24, la annulla e controlla quali file sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Creo in background una notifica per destinatario tramite API REST
      | modello           | 890                |
      | at                | Presso             |
      | indirizzo         | VIA ROMA 20        |
      | dettagliIndirizzo | Scala b            |
      | codicePostale     | 20147              |
      | comune            | Milano             |
      | dettagliComune    | Milano             |
      | provincia         | Mi                 |
      | stato             | Italia             |
      | nomeCognome       | Gaio Giulio Cesare |
      | codiceFiscale     | CSRGGL44L13H501E   |
      | tipoDestinatario  | PF                 |
      | domicilioDigitale | test@test.com      |
      | documenti         | 1                  |
      | multidestinatario | false              |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
      | costiNotifica     | true               |
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    Then Si controlla sia presente il modello F24
    Then Si controlla sia presente l'avviso PagoPa
    Then Si clicca l'avviso PagoPa
    Then Si torna alla pagina precedente
    And Logout da portale mittente