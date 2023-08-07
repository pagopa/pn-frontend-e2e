Feature: Il delegato visualizza la notifiche del delegante

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

    @TestSuite
    @test50


  Scenario: Il delegato visualizza la notifiche del delegante
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Si visualizza l elenco delle notifiche relative al delegante
    And Si seleziona il nome del delegante nell elenco
    Then Si visualizzano tutte le notifiche del delegante selezionato
    And  Logout da portale persona fisica