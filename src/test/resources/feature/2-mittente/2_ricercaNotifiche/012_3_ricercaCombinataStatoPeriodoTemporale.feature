Feature: il mittente fa una ricerca combinata tra stato e arco temporale

  @TestSuite
    @mittente
    @ricercaNotificheMittente
    @TA_MittenteRicercaPerStatoPeriodo

  Scenario Outline: PN-9222 - il mittente fa una ricerca sia per arco temporale che per stato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Aspetta 20 secondi
    When Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con arco temporale e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      | stato                               |
      | Perfezionata per decorrenza termini |