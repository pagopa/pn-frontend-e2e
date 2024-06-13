Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test81


  Scenario: PN-9609 - Ottenere la storia non anonimizzata di una PF il suo codice univoco
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice univoco "PF-a01b62d4-e3f8-48f3-a4d8-cf628a34b745"
    And Cliccare sul bottone ricerca
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "dati.txt"
    And Si elimina file estratto
    And Si clicca sul bottone resetta filtri
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice univoco "PF-a01b62d4-e3f8-48f3-a4d8-cf628a34b745"
    And viene inserito un range temporale maggiore di 3 mesi
    And viene visualizzato messaggio di errore data
    And Si clicca sul bottone resetta filtri
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice univoco "PF-a01b62d4-e3f8-48f3-a4d8-cf628a34b799"
    And Cliccare sul bottone ricerca
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "error.txt"
    And Si elimina file estratto



