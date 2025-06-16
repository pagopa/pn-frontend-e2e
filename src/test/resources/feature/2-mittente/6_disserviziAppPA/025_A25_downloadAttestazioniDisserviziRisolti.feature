Feature: Mittente effettua il download delle attestazioni dei disservizi risolti

  @TestSuite
  @TA_MittenteDownloadDisservizioRisolto
  @NRT_Blocco_1

  Scenario: PN-9238 - Mittente effettua il download delle attestazioni dei disservizi risolti
    Given Creazione disservizio new su portale helpdesk
    And Aspetta 3 secondi
    And Risoluzione disservizio new su portale helpdesk
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    And Si visualizza un record in elenco relativo ad un disservizio risolto "disponibile a breve"
    And Aspetta 1 secondi
    And Download file attestazione disservizio 1