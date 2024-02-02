Feature: Mittente effetua una ricerca notifiche per Stato

  @TestSuite
    @TA_MittenteRicercaPerStato
    @mittente
    @ricercaNatoficheMittente

  Scenario Outline: PN-9324 - Mittente logato effettua una ricerca per stato notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica <stato>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente

    Examples:
      | stato            |
      | Avvenuto accesso |
    # ci sono altri stati come annullata, invio in corso, consegnata ecc.