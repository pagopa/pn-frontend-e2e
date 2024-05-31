Feature: Il destinatario apre una notifica con pagamento annullata

  @checkDownloadFileNotificaAnnullata
  Scenario: [TA-FE DESTINATARIO APRE UNA NOTIFICA CON PAGAMENTO ANNULLATA] - Il destinatario apre una notifica con pagamento annullata, non vede la CTA Paga e non può scaricare i file per il pagamento
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "YQTL-RMKD-WMDG-202405-N-1"
    And Si clicca la notifica ricercata
    And Si controlla non sia presente il bottone paga
    Then Si controlla non sia presente l'avviso PagoPa PG
    Then Si controlla non sia presente il modello F24 PG
    And Logout da portale persona fisica