Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test83


  Scenario: PN-9614 - Resettare il form di ricerca
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice IUN senza ricerca "YRUZ-NYXJ-DAJK-202405-N-1"
    And Cliccare sul bottone ricerca
    And controllo messaggio di successo
    And Si clicca sul bottone resetta filtri
    And Si verifica che i campi sono puliti




