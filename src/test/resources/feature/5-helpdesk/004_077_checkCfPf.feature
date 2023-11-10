Feature: utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test77


  Scenario: visualizzazione corretta pagina sezione ricerca ed estrazione dati in helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And viene inserito codice fiscale "personaFisica"
    And controllo generazione codice univoco
    And selezione ricerca codice fiscale
    Then controllo corrispondenza codice fiscale

