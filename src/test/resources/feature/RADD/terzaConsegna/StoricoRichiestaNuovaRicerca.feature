Feature: Nuova ricerca per storico richieste per atti opponibili a terzi

      #test sul bottone Nuova ricerca per la ricerca dello storico delle richieste per atti opponibili a terzi

  Background: Accesso alla pagina Storico delle richieste

    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
    Then la pagina Storico delle richieste è visualizzata correttamente

    @RADD
    @TestSuite

  Scenario: bottone Nuova ricerca per ricerca storico richieste
    When Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato
    And Nella pagina Storico delle ricerche il radio button Codice IUN è selezionato
    And Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN da ricercare da "RADD"
    And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
    And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi viene restituita la richiesta con il codice IUN ricercato
    And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sul bottone Nuova ricerca
    And la pagina Storico delle richieste è visualizzata correttamente