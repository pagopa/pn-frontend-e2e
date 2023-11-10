Feature: la persona fisica modifica l'indirizzo pec già presente

  Background:  Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @personaFisicaDestinatario
  @TA_modificaPECPF
  @recapiti

  Scenario: la persona fisica modifica l'indirizzo pec già presente
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC della persona fisica "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone salva
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    Then Nella pagina I Tuoi Recapiti si verifica che la pec sia stata modificata "prova@pec.it"
    And Logout da portale persona fisica