Feature: il mittente fa una ricerca combinata tra cf e stato

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
    @mittente
    @ricercaNatoficheMittente
    @TA_MittenteRicercaPerCFeStato

  Scenario Outline: PN-9222 - il mittente fa una ricera sia per cf che per stato
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      | stato                               |
      | Perfezionata per decorrenza termini |