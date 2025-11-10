@NRT_Blocco_2
Feature: Il destinatario accede ad una notifica non ancora pagata e scarica i file

  @downloadFileNotificaConPagamento

  Scenario: [TA-FE DOWNLOAD FILE NOTIFICA CON PAGAMENTO] - Il destinatario accede ad una notifica con pagamento scarica il modello F24
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "KZTL-KLPK-DRZU-202508-N-1"
    #And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "QNUE-WLWY-TJRK-202409-Y-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si torna alla pagina precedente

  @downloadFileNotificaConPagamento

  Scenario: [TA-FE DOWNLOAD FILE NOTIFICA CON PAGAMENTO] - Il destinatario accede ad una notifica con pagamento e scarica l'avviso PagoPa
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "KZTL-KLPK-DRZU-202508-N-1"
    #And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "QNUE-WLWY-TJRK-202409-Y-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si torna alla pagina precedente
