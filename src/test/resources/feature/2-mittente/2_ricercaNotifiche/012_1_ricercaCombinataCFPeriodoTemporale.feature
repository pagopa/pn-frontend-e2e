Feature: il mittente fa una ricerca combinata tra cf e periodo temporale

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittenteRicercaPerCFePertiodo
  @mittente
  @ricercaNatoficheMittente

  Scenario: il mittente fa una ricera sia per cf che per periodo temporale
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito
    And Logout da portale mittente