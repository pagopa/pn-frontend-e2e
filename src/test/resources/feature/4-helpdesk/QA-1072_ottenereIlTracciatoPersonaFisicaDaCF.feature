Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

#  Background: Login utente in helpdesk
#    Given Login helpdesk con utente test
#    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @OttenereTracciatoNonAnonimizzataDiPersonaFisicaDaCodiceFiscale
  @NRT_Blocco_3
  @helpDesk
  Scenario: [QA-1072] - Ottenere  il tracciato non anonimizzata di una persona fisica dato il suo codice fiscale
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice fiscale senza ricerca "CSRGGL44L13H501E"
    And Cliccare sul bottone ricerca
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "dati.txt"
    And Si elimina file estratto
    And Si clicca sul bottone resetta filtri
    # Caso negativo: step funzionanti ma aws non riesce a completarli per una diversa gestione
    # del caricamento delle icone calendario. Gli step sono stati commentati
    # And Selezione ottieni log completi
    # And viene inserito numero ticket
    # And viene inserito codice fiscale
    # And viene inserito un range temporale maggiore di 3 mesi
    # And viene visualizzato messaggio di errore data
    # And Si clicca sul bottone resetta filtri
    # Caso PF non esistente
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice fiscale senza ricerca "TYRMLK90T20Z253O"
    And Cliccare sul bottone ricerca
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento estratto da zip e che sia vuoto "dati.txt"
    And Si elimina file estratto





