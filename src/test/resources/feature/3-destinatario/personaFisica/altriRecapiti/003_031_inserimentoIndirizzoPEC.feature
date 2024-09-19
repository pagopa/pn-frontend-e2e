Feature: la persona fisica inserisce una email pec

  @TestSuite
  @TA_inserimentoPECPF
  @PF
  @recapitiPF

  Scenario: PN-9240-A31 - La persona fisica inserisce una email pec
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    When Nella pagina I Tuoi Recapiti si visualizza correttamente il riquadro relativo alla PEC
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "pectest@pec.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    Then Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
    And Logout da portale persona fisica