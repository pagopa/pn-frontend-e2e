Feature: il mittente fa una ricerca combinata tra stato e arco temporale
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
   When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
    @mittente
    @ricercaNatoficheMittente
    @TA_MittenteRicercaPerStatoPeriodo

  Scenario Outline: il mittente fa una ricerca sia per arco temporale che per stato
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con arco temporale e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      |     stato      |
      | Perfezionata per decorrenza termini |