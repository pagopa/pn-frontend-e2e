Feature: La persona giuridica inserisce una OTP sbagliato PEC

  @TestSuite
  @PG
  @recapitiPG
  @TA_inserimentoOTPErratoPG

  Scenario: PN-9152-D60 - La persona giuridica loggato inserisce un OTP sbagliato PEC
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella pagina I Tuoi Recapiti clicca sul bottone conferma
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP scaduto "personaGiuridica"
    And Si visualizza correttamente il messaggio di errore
    And Si clicca sul link generane uno nuovo OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    Then Si verifica che la PEC stato inserito
    And Logout da portale persona giuridica
