Feature:Pagamento notifica

  @TestSuite
  @PF
  @PagamentoNotificaPF

  @PagamentoNotificaPF
  Scenario:[NOTIFICA-PAGAMENTO NOTIFICA AVVISO PAGOPA] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Creo in background una notifica per destinatario tramite API REST
      | destinatario      | PF    |
      | documenti         | 1     |
      | multidestinatario | false |
      | avvisoPagoPa      | 2     |
      | F24               | 1     |
      | costiNotifica     | false |
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Si seleziona la notifica
