Feature: Si verifica la corretta visualizzazione di notifiche non ancora pagate

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Visualizzazione notifica non pagata mono destinatario con solo avviso PagoPa
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "QNMY-DXKH-LEDW-202405-J-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento
    And Logout da portale mittente

