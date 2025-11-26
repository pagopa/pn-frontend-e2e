@newBlock
Scenario: Login pagoPA mittente
Given l'utente tenta di navigare alla pagina LoginPage
When l'utente Utente tenta la login
  # aspetta il caricamento dell'header, panoramica area riservata, verifica del codice fiscale
Then Home page mittente viene visualizzata correttamente
And Click entra su Send Mittente
And Logout da portale mittente
