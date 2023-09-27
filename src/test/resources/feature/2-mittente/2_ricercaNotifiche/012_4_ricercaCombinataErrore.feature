Feature: il mittente fa una ricerca combinata tra stato e arco temporale  con nessun risultato
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite

    @test12_4

  Scenario Outline: il mittente fa una ricerca sia per arco temporale che per stato con nessun risultato
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema non restituisce notifiche
    And Logout da portale mittente
    Examples:
      |     stato      |
      |   Annullata    |