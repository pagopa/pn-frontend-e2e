Feature: Storico richieste per Avvisi di avvenuta ricezione con CF con KO

        #Ricerca dello storico delle richieste tramite CF per Avvisi di avvenuta ricezione con KO
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

  @RADD
  @TestSuite
   
Scenario: Ricerca storico richieste per CF con KO
   When Nella pagina Storico delle ricerche selezionare il radio button Avvisi di avvenuta ricezione
    And Nella pagina Storico delle ricerche selezionare il radio button Codice fiscale destinatario
    And Nella pagina Storico delle ricerche inserire nel campo di input il codice fiscale errato per AAR del destinatario "RADD"
    And Nella pagina Storico delle ricerche inserire nei campi di input una data
    And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
    Then Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per codice fiscale