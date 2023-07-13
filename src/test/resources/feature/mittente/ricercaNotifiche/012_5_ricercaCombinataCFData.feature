Feature: il mittente fa una ricerca combinata tra cf e data
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @LoginMittenteRest
  @test12_5

  Scenario: il mittente fa una ricera sia per cf che per data
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale del destinatario "destinatario"
    And Nella pagina Piattaforma Notifiche inserire una data
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e data uguale a quelli inserito
    And Logout da portale mittente