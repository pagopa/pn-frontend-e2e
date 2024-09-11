@TestSuite
Feature: Il destinatario apre una notifica con pagamento annullata

  @checkDownloadFileNotificaAnnullata
  Scenario: [TA-FE DESTINATARIO APRE UNA NOTIFICA CON PAGAMENTO ANNULLATA] - Il destinatario apre una notifica con pagamento annullata, non vede la CTA Paga e non può scaricare i file per il pagamento
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina piattaforma notifiche PG si effettua la ricerca per codice IUN "QEQJ-VYZQ-ULDE-202408-H-1"
    And Si clicca la notifica ricercata
    And Si controlla non sia presente il bottone paga
    Then Si controlla non sia presente l'avviso PagoPa
    And Si controlla non sia presente il modello F24 destinatario
    And Logout da portale persona giuridica