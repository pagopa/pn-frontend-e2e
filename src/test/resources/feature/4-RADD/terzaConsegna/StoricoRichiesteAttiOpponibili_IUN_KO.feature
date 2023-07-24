Feature: Storico richieste per atti opponibili a terzi con IUN con KO

        #Ricerca dello storico delle richieste tramite IUN per Documenti allegati e attestazioni opponibili a terzi con KO
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

   @RADD

   
Scenario: Ricerca storico richieste per IUN No elementi associati con KO
   When Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato
   And Nella pagina Storico delle ricerche il radio button Codice IUN è selezionato
   And Nella pagina Storico delle ricerche inserire nel campo di testo il codice IUN errato da ricercare da "RADD"
   And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
   Then Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per codice IUN
  
   