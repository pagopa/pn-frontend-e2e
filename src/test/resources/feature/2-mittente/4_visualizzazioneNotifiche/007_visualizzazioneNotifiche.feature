Feature: Mittente visualizza correttamente la pagina notifiche

  @TestSuite
  @TA_MittentevisualizzazioneNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9216 - Mittente visualizza correttamente la pagina notifiche
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca
    Then Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche
    And Logout da portale mittente



