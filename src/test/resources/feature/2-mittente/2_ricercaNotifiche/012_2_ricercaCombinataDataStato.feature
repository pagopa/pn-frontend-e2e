Feature: il mittente fa una ricerca combinata tra stato e data

  @TestSuite
    @mittente
    @ricercaNotificheMittente
    @TA_MittenteRicercaPerDataStato

  Scenario Outline: PN-9222 - il mittente fa una ricerca sia per data che per stato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire una data
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con data e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      | stato                               |
      | Perfezionata per decorrenza termini |