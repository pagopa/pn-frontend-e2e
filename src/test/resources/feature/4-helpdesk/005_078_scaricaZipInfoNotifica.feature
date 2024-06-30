Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @OttenereInfoCompletaDiNotifica
  @customtest


  Scenario: PN-9606 - Ottenere le informazioni complete di una notifica
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni notifica
    And viene inserito codice IUN "YRUZ-NYXJ-DAJK-202405-N-1"
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "dati.txt"
    And Si elimina file estratto
    And Si clicca sul bottone resetta filtri
    And  Selezione ottieni notifica
    And viene inserito codice IUN "YRUZ-NYXJ-DAJK-202405-N-2"
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "error.txt"
    And Si elimina file estratto
