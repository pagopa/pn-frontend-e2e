Feature: il mittente fa una ricerca combinata tra cf e periodo temporale
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite

  @test12_1

  Scenario: il mittente fa una ricera sia per cf che per periodo temporale
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito
    And Logout da portale mittente