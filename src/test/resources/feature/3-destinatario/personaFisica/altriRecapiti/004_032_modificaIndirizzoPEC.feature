Feature: la persona fisica modifica l'indirizzo pec già presente
  
  @TestSuite
  @PF
  @TA_modificaPECPF
  @recapitiPF

  Scenario: PN-9306-D32 - la persona fisica modifica l'indirizzo pec già presente
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "personaFisica"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone salva
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    Then Nella pagina I Tuoi Recapiti si verifica che la pec sia stata modificata "personaFisica"
    And Logout da portale persona fisica