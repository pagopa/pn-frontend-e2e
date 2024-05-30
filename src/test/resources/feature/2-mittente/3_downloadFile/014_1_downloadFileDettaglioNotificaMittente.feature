Feature: Mittente scarica tutti i file all'interno di una notifica

  @TestSuite
  @TA_MittenteDownloadFileDettaglioNotifica
  @mittente
  @DownloadFileMittente

  Scenario: PN-9327 - Mittente scarica attestazioni
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Creo in background una notifica con un destinatario e un documento tramite API REST
      | avvisoPagoPa | false |
      | F24          | false |
      | entrambi     | true  |
    Then Attendo 5 minuti e verifico in background che la notifica sia stata creata correttamente
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    #Then Nella sezione Dettaglio Notifica si scaricano tutti i file presenti
    And Logout da portale mittente