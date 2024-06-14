Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test84


  Scenario: PN-9607-b - Ottenere la storia non anonimizzata di una PF il suo codice fiscale
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice fiscale senza ricerca "CSRGGL44L13H501E"
    And viene inserito un range temporale maggiore di 3 mesi
    And viene visualizzato messaggio di errore data
    And Si clicca sul bottone resetta filtri
    And viene inserito numero ticket
    And viene inserito codice fiscale senza ricerca "1111111111111111"
    And Cliccare sul bottone ricerca
    And viene visualizzato messaggio di errore CF




