Feature: Mittente verifica il campo stato notifica del filtro di ricerca

  @TestSuite
  @TA_MittenteVerificaCampoStatoFiltroRicerca
  @mittente
  @ricercaNatoficheMittente

  Scenario: PN-9221 - Mittente logato verifica il menu a tendina del campo stato notifica del filtro di ricerca
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche
    Then Nella pagina piattaforma Notifiche è presente un campo di ricerca con un menu a tendina per selezionare lo stato della notifica
    And Logout da portale mittente
