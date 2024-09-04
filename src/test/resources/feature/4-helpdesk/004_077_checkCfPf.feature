Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test77
  Scenario: PN-9605 - Visualizzazione corretta pagina sezione ricerca ed estrazione dati in helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And viene inserito codice fiscale
    |codiceFiscale|CSRGGL44L13H501E|
    And controllo generazione codice univoco
    And selezione ricerca codice fiscale
    Then controllo corrispondenza codice fiscale

