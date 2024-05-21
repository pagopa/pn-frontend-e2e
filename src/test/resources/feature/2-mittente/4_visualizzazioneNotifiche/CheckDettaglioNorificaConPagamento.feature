Feature: Si verifica la corretta visualizzazione di notifiche non ancora pagate

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Visualizzazione notifica non pagata mono destinatario solo con avviso PagoPa
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "QNMY-DXKH-LEDW-202405-J-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente l'avviso PagoPa
    And Logout da portale mittente

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Visualizzazione notifica non pagata mono destinatario solo con modello F24
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JVDU-KLJG-RPZV-202405-X-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Visualizzazione notifica non pagata mono destinatario solo con avviso PagoPa e modello F24
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JHME-HQME-DGEL-202405-E-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente l'avviso PagoPa
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Visualizzazione notifica non pagata multi destinatario solo con avviso PagoPa
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "PTUY-UJWN-YAQU-202405-L-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Logout da portale mittente

  @checkNotificaNonPagata
  Scenario: [TA-FE CHECK NOTIFICA NON PAGATA] - Notifica multi destinatario non pagata, si seleziona un destinatario e si controllano gli avvisi relativi
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "PTUY-UJWN-YAQU-202405-L-1"
    And Si clicca la notifica ricercata
    Then Si controlla sia presente il box per il pagamento del multidestinatario
    And Si seleziona un destinatario
    Then Si controlla sia presente l'avviso PagoPa
    And Logout da portale mittente
