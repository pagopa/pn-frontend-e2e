Feature: persona giuridica scarica attestazioni all'interno di una notifica

  @TestSuite
  @TA_PG_DownloadFile
  @DownloadFilePG
  @PG

  Scenario: PN-9151 - Persona giuridica scarica attestazione
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "EGNM-DPAR-VTLR-202401-T-1"
    And Cliccare sul bottone Filtra persona giuridica
    When La persona giuridica clicca sulla notifica restituita "EGNM-DPAR-VTLR-202401-T-1"
    And Si visualizza correttamente la section Dettaglio Notifica persona giuridica delegato
    And Si controlla se la notifica prevede il pagamento
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona giuridica, e si controlla che il download sia avvenuto "datiNotificaPG"
    And Si clicca sul opzione Vedi Dettaglio
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And Logout da portale persona giuridica