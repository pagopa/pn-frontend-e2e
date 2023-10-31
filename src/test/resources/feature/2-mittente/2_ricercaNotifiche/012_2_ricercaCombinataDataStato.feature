Feature: il mittente fa una ricerca combinata tra stato e data
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
   When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite

  @test12_2

  Scenario Outline: il mittente fa una ricerca sia per data che per stato
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire una data
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con data e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
    Examples:
      |     stato      |
      | Perfezionata per decorrenza termini |