Feature: Mittente effettua il download delle attestazioni dei disservizi risolti

  @TestSuite
  @mittente
  @TA_MittenteDownloadDisservizioRisolto
  @File
    #per il funzionamento corretto del test su aws, il parametro di configurazione headless deve essere settato a true

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
    And Si visualizza un record in elenco relativo ad un disservizio risolto "Invio delle notifiche"
    And Download file attestazione disservizio 1
    And Controllo corrispondenza dati con pdf
    And Logout da portale mittente


