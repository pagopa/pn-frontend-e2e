Feature: Mittente visualizza il dettaglio di una notifica

  @TestSuite
  @TA_MittenteDettaglioNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9225 - Mittente visualizza dettaglio notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli
    And Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso
    Then Si clicca sul bottone indietro
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Logout da portale mittente
