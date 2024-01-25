Feature: la persona fisica inserisce un indirizzo pec aggiuntivo

  @TestSuite
  @TA_PECAggiuntivaPF
  @recapitiPF
  @PF

  Scenario: PN-9318-D40 - la persona fisica inserisce un indirizzo pec aggiuntivo
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già la nuova pec
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti
    And Nella sezione altri recapiti si controlla l'esistenza di una PEC "personaFisica"
    And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica "personaFisica"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    Then Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente
    And Logout da portale persona fisica
