Feature: Mittente effettua il download delle attestazioni dei disservizi risolti

  @TA_MittenteDownloadDisservizioRisolto
  @bilinguismo
  @helpDesk
  @NRT_Blocco_1

  Scenario: PN-9238 - Mittente effettua il download delle attestazioni dei disservizi risolti
    Given Creazione disservizio new su portale helpdesk
    And Refresh pagina
    And Risoluzione disservizio new su portale helpdesk
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Refresh pagina
    And Download file attestazione disservizio 1