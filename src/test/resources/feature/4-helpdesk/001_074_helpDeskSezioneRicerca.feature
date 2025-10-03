Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  @test74
  @NRT_Blocco_3
  @helpDesk
  Scenario: PN-9602 - Visualizzazione corretta pagina sezione ricerca ed estrazione dati in helpdesk
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    Then visualizzazione corretta pagina ricerca ed estrazione dati

