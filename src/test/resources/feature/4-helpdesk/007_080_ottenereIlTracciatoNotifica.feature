Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @OttenereTracciatoNonAnonimizzataDiNotifica
  @customtest


  Scenario: PN-9608 - Ottenere  il tracciato non anonimizzata di una notifica dato il suo IUN
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice IUN senza ricerca "YRUZ-NYXJ-DAJK-202405-N-1"
    And Spuntare la casella Deanonimizzazione dati
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
    And viene inserito codice IUN "YRUZ-NYXJ-DAJK-20240"
    And viene visualizzato messaggio di errore IUN
    And Si clicca sul bottone resetta filtri
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice IUN senza ricerca "YRUZ-NYXJ-DAJK-202405-N-3"
    And Cliccare sul bottone ricerca
    And controllo password
    # su aws non e possibile scaricare il zip con IUN non valido
   # And controllo link per scaricare zip e scarico file
   # And Aspetta 5 secondi
   # And Inserisco la password ed estraggo il file zip
   # And Controllo sia presente documento "error.txt"
   # And Si elimina file estratto





