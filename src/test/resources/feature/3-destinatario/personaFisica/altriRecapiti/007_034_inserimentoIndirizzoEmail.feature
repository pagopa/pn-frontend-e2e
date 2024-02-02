Feature: la persona fisica inserisce una Email

  @TestSuite
  @TA_inserimentoEmailPF
  @recapitiPF
  @PF

  Scenario: PN-9308-A34 - la persona fisica inserisce una Email
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email "personaFisica"
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia presente
    And Logout da portale persona fisica