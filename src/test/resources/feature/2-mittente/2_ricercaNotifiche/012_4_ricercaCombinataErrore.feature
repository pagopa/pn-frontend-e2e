Feature: il mittente fa una ricerca combinata tra stato e arco temporale  con nessun risultato

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
    @mittente
    @ricercaNatoficheMittente
    @TA_MittenteRicercaSenaRisultatoPerStatoPeriodo

  Scenario Outline: PN-9325 - il mittente fa una ricerca sia per arco temporale che per stato con nessun risultato
    When Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema non restituisce notifiche
    And Logout da portale mittente
    Examples:
      | stato     |
      | Annullata |