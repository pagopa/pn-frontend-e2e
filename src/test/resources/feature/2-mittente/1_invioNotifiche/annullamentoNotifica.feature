Feature: annullamento della notifica

  @annullamentoNotificaPF
  Scenario: [TA-FE MITTENTE CREA E ANNULLA UNA NOTIFICA CON PAGAMENTO] - Mittente invia una notifica con avviso PagoPa e F24, la annulla e controlla quali file sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Creo in background una notifica con un destinatario e un documento tramite API REST
      | avvisoPagoPa | false |
      | F24          | false |
      | entrambi     | true  |
    Then Attendo 4 minuti e verifico in background che la notifica sia stata creata correttamente
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