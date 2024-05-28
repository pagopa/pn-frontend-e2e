Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TestSuite
  @TA_MittentevisualizzazioneNotifichaAnnullataConPagamento
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-10243 - Mittente visualizza correttamente la notifica in stato Annullato con Pagamento
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN "WVEM-RAVW-HLYV-202405-T-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si visualizza correttamente box di pagamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Logout da portale mittente



