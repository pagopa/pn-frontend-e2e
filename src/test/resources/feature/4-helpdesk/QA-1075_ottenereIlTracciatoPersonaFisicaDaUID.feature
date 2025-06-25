Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

#  Background: Login utente in helpdesk
#    Given Login helpdesk con utente test
#    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @OttenereTracciatoNonAnonimizzataDiPersonaFisicaDaCodiceUnivoco
  @NRT_Blocco_3_GRUPPO_AWS
  @helpDesk
  Scenario: [QA-1075] - Ottenere  il tracciato non anonimizzata di una persona fisica dato il suo codice univoco
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And viene inserito codice fiscale
    And controllo generazione codice univoco
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
    # Controllo valore cx_id nel file di testo
#    And Controllo sia presente documento estratto da zip con testo "dati.txt" "PF-a01b62d4-e3f8-48f3-a4d8-cf628a34b745"
    # Controllo valore uid nel file di testo
#    And Controllo sia presente documento estratto da zip con testo "dati.txt" "a01b62d4-e3f8-48f3-a4d8-cf628a34b745"
    And Si elimina file estratto
    And Si clicca sul bottone resetta filtri
    # Caso negativo: step funzionanti ma aws non riesce a completarli per una diversa gestione
    # del caricamento delle icone calendario. Gli step sono stati commentati
    # And Selezione ottieni log completi
    # And viene inserito numero ticket
    # And viene inserito codice univoco "PF-a01b62d4-e3f8-48f3-a4d8-cf628a34b745"
    # And viene inserito un range temporale maggiore di 3 mesi
    # And viene visualizzato messaggio di errore data
    # And Attendi secondi "10"
    # And Si clicca sul bottone resetta filtri
    # Caso PF non esistente
    And Selezione ottieni log completi
    And viene inserito numero ticket
    And viene inserito codice univoco "PF-00000000-0000-0000-0000-000000000000"
    And Cliccare sul bottone ricerca
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento estratto da zip e che sia vuoto "dati.txt"
    And Si elimina file estratto





