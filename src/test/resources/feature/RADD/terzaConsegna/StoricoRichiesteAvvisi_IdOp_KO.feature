Feature: Storico richieste per Avvisi di avvenuta ricezione con Id Operazione con KO

        #Ricerca dello storico delle richieste tramite Id operazione per Avvisi di avvenuta ricezione con KO
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

   @RADD
   @TestSuite
   
Scenario: Ricerca storico richieste per Id operazione con KO
   When Nella pagina Storico delle ricerche selezionare il radio button Avvisi di avvenuta ricezione
   And Nella pagina Storico delle ricerche selezionare il radio button Id Operazione
   And Nella pagina Storico delle ricerche inserire nel campo di testo l'id Operazione errato "RADD" da ricercare
   And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
   Then Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per id operazione
   