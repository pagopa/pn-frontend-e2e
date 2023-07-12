Feature: Storico richieste per avvisi avvenuta ricezione a terzi con Codice IUN

  Background: Accesso alla pagina Storico delle richieste

    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
    Then la pagina Storico delle richieste è visualizzata correttamente

  @RADD
  @TestSuite

  Scenario: Ricerca storico richieste per CF
    When Nella pagina Storico delle ricerche il radio button avvisi avvenuta ricezione è selezionato
    And Nella pagina Storico delle ricerche il radio button Codice IUN è selezionato
    And Nella pagina Storico delle ricerche inserire nel campo di input il codice IUN del destinatario AAR "RADD"
    And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
    And Nella pagina Risultato ricerca avvisi avvenuta ricezione vengono restituite le richieste con il codice IUN del destinatario ricercato
    And Nella pagina Risultato ricerca avvisi avvenuta ricezione cliccare su una richiesta
    And La modale Dettaglio della richiesta ID richiesta viene visualizzata correttamente
    Then Nella modale cliccare sul bottone Chiudi
   #Then la pagina Storico delle richieste è visualizzata correttamente