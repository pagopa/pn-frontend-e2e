Feature: Mittente visualizza correttamente la pagina notifiche

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittentevisualizzazioneNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9216 - Mittente visualizza correttamente la pagina notifiche
    When Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca
    Then Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche
    And Logout da portale mittente



