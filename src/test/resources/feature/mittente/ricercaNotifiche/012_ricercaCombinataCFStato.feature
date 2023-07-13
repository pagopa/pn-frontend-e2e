Feature: il mittente fa una ricerca combinata tra cf e stato
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
    @LoginMittenteRest
  @test12

  Scenario Outline: il mittente fa una ricera sia per cf che per stato
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale del destinatario "destinatario"
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e stato uguale a quelli inserito <stato>
    And Logout da portale mittente
  Examples:
    |     stato      |
    |  Consegnata    |