Feature: Login pagoPA demo

Scenario: Login pagoPA mittente
  Given l'utente Grossini effettua l'accesso a SelfCare con autenticazione SPID
  When l'utente accede alla dashboard selezionado "Comune di Palermo"
#When l'utente Grossini tenta la login
#Then Home page mittente viene visualizzata correttamente
#And Click entra su Send Mittente
#And Logout da portale mittente
