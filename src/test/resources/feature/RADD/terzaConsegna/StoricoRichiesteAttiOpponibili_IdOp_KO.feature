Feature: Storico richieste per atti opponibili a terzi con Id operazione con KO

      #Ricerca dello storico delle richieste tramite Id Operazione per Documenti allegati e attestazioni opponibili a terzi con KO
  

Background: Accesso alla pagina Storico delle richieste

   Given operatore è loggato
   And la Homepage RADD è visualizzata correttamente
   When nella Homepage RADD sezione Storico delle ricerche clicca sul bottone con freccia
   Then la pagina Storico delle richieste è visualizzata correttamente

   @RADD
   
Scenario: Ricerca storico richieste per Id operazione con KO
   When Nella pagina Storico delle ricerche il radio button Documenti allegati e attestazioni opponibili a terzi è selezionato
   And Nella pagina Storico delle ricerche selezionare il radio button Id Operazione 
   And Nella pagina Storico delle ricerche inserire nel campo di testo Id operazione errato "RADD" da ricercare
   And Nella pagina Storico delle ricerche cliccare sul bottone Cerca
   Then Nella pagina Storico delle ricerche viene visualizzato un pop up con l'etichetta KO per id operazione
   