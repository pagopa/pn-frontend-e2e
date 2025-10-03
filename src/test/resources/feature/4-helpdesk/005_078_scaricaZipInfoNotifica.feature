Feature: Utente helpdesk visualizza pagina sezione ricerca ed estrazione dati

  @OttenereInfoCompletaDiNotifica
  @NRT_Blocco_3
  @helpDesk
  Scenario: PN-9606 - Ottenere le informazioni complete di una notifica
    Given Login helpdesk con utente test
    And Si visualizza correttamente home Helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni notifica
    And viene inserito codice IUN "IUN1"
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "dati.txt"
    And Si elimina file estratto
    And Si clicca sul bottone resetta filtri
    And  Selezione ottieni notifica
    And viene inserito codice IUN "IUN2"
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "error.txt"
    And Si elimina file estratto
