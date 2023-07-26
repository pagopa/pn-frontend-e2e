Feature: Storico richieste per atti opponibili a terzi con IUN

        #Ricerca dello storico delle richieste tramite IUN per Documenti allegati e attestazioni opponibili a terzi
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

   @RADD

   
Scenario: Ricerca storico richieste per IUN No elementi associati
   When Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato
   And Nella pagina Storico delle ricerche il radio button Codice IUN è selezionato
   And Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN da ricercare da "RADD"
   And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
   And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi viene restituita la richiesta con il codice IUN ricercato
   And Nella pagina Risultato ricerca Documenti allegati e attestazioni opponibile a terzi cliccare sulla richiesta restituita
   And La modale Dettaglio della richiesta ID richiesta viene visualizzata correttamente
   Then Nella modale cliccare sul bottone Chiudi
   #Then la pagina Storico delle richieste è visualizzata correttamente
  
   