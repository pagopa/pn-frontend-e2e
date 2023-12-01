Feature: il mittente fa una ricerca combinata tra stato e data

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
    @mittente
    @ricercaNatoficheMittente
    @TA_MittenteRicercaPerDataStato

  Scenario Outline: il mittente fa una ricerca sia per data che per stato
    When Nella pagina Piattaforma Notifiche inserire una data
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con data e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      | stato                               |
      | Perfezionata per decorrenza termini |