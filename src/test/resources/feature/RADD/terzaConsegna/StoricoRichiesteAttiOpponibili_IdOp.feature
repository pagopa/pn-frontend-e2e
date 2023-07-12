Feature: Storico richieste per atti opponibili a terzi con Id operazione

      #Ricerca dello storico delle richieste tramite Id Operazione per Documenti allegati e attestazioni opponibili a terzi
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

 @RADD
 @TestSuite
   
Scenario: Ricerca storico richieste per Id operazione
   When Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato
   And Nella pagina Storico delle ricerche selezionare il radio button Id Operazione 
   And Nella pagina Storico delle ricerche inserire nel campo di testo l' Id operazione "RADD" da ricercare
   And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
   And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi viene restituita la richiesta con l'id operazione ricercato
   And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sulla richiesta restituita idOP
   And La modale Dettaglio della richiesta ID richiesta viene visualizzata correttamente
   Then Nella modale cliccare sul bottone Chiudi
   #Then la pagina Storico delle richieste è visualizzata correttamente
   