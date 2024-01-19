Feature: Mittente scarica tutti i file all'interno di una notifica


  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteDownloadFileDettaglioNotifica
  @mittente
  @DownloadFileMittente

  Scenario: PN-9327 - Mittente scarica attestazioni
    When Creo in background una notifica con un destinatario e un documento tramite API REST
    Then Attendo 3 minuti e verifico in background che la notifica sia stata creata correttamente
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    Then Nella sezione Dettaglio Notifica si scaricano tutti i file presenti
    And Logout da portale mittente