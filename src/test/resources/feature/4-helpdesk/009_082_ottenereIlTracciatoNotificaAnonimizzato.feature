Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @OttenereTracciatoAnonimizzataDiNotifica


  Scenario: PN-9610 - Ottenere  il tracciato anonimizzata di una notifica dato il suo IUN
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice IUN senza ricerca "YRUZ-NYXJ-DAJK-202405-N-1"
    And Cliccare sul bottone ricerca
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "dati.txt"
    And Si elimina file estratto




