Feature: Mittente scarica tutti i file all'interno di una notifica

  @TestSuite
  @TA_MittenteDownloadFileDettaglioNotifica
  @mittente
  @DownloadFileMittente

  Scenario: PN-9327 - Mittente scarica attestazioni
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Creo in background una notifica per destinatario tramite API REST
      | destinatario      | PF    |
      | documenti         | 1     |
      | multidestinatario | false |
      | avvisoPagoPa      | 1     |
      | F24               | 1     |
      | costiNotifica     | true  |
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    Then Nella sezione Dettaglio Notifica si scaricano tutti i file presenti
    And Logout da portale mittente