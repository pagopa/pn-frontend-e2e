Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test74


  Scenario: PN-9602 - Visualizzazione corretta pagina sezione ricerca ed estrazione dati in helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    Then visualizzazione corretta pagina ricerca ed estrazione dati

