Feature: il mittente fa una ricerca combinata tra cf e periodo temporale
  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @test12_1

  Scenario: il mittente fa una ricera sia per cf che per periodo temporale
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale da destinatario "destinatario"
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito
    And Logout da portale mittente