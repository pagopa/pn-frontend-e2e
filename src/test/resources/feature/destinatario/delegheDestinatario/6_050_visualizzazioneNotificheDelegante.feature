Feature: Il delegato visualizza la notifiche del delegante

  Background: Login delegato
    Given Login Page destinatario "delegato" viene visualizzata
    When Login portale destinatario tramite request method
    Then Home page destinatario viene visualizzata correttamente

    @TestSuite
    @test50
    @restLogin


  Scenario: Il delegato visualizza la notifiche del delegante
    When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
    And Si visualizza l elenco delle notifiche relative al delegante
    And Si seleziona il nome del delegante nell elenco
    Then Si visualizzano tutte le notifiche del delegante selezionato
    And  Logout da portale destinatario