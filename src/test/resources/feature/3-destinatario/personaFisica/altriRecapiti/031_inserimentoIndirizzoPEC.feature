Feature: la persona fisica inserisce una email pec

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente


  @inserimentoPECPF

  Scenario: la persona fisica inserisce una email pec
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce la PEC del persona fisica "personaFisica"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupare il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    Then Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
    And Logout da portale persona fisica