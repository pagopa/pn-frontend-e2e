Feature: Mittente scarica tutti i file all'interno di una notifica


  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteDownloadFileDettaglioNotifica
  @mittente
  @DownloadFileMittente

  Scenario: Mittente scarica attestazioni
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    Then Nella sezione Dettaglio Notifica si scaricano tutti i file presenti
    And Logout da portale mittente