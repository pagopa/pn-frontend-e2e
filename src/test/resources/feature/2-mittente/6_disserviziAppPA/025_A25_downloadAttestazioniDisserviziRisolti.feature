Feature: Mittente effettua il download delle attestazioni dei disservizi risolti

  @TestSuite
  @mittente
  @TA_MittenteDownloadDisservizioRisolto
  @TA_PA_DownloadDisservizioRisolto

  Scenario: PN-9238 - Mittente effettua il download delle attestazioni dei disservizi risolti
    Given Creazione disservizio su portale helpdesk
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    Then Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente
    And Si visualizza correttamente la tabella dei disservizi
    And Si visualizza un record in elenco relativo ad un disservizio ancora in corso
    And Risoluzione disservizio su portale helpdesk
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    Then Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente
    And Si visualizza correttamente la tabella dei disservizi
    And Si visualizza un record in elenco relativo ad un disservizio risolto
    And Visualizza file di un disservizio risolto, "Attestazione opponibile a terzi"
    And Logout da portale mittente

