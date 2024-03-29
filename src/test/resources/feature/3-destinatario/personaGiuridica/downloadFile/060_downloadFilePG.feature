Feature: persona giuridica scarica attestazioni all'interno di una notifica

  @TestSuite
  @TA_PG_DownloadFile
  @DownloadFilePG
  @PG

  Scenario: PN-9151 - Persona giuridica scarica attestazione
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "datiNotificaPG"
    And Cliccare sul bottone Filtra persona fisica
    When La persona giuridica clicca sulla notifica restituita "datiNotificaPG"
    And Si visualizza correttamente la section Dettaglio Notifica persona giuridica
    And Si controlla se la notifica prevede il pagamento
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona giuridica, e si controlla che il download sia avvenuto "datiNotificaPG"
    And Si clicca sul opzione Vedi Dettaglio
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And Logout da portale persona giuridica