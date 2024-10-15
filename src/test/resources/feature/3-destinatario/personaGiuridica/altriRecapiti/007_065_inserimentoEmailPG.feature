Feature: La persona giuridica inserisce l'email

  @TestSuite
  @TA_inserimentoEmailPG
  @PG
  @recapitiPG

  Scenario: PN-9155 - La persona giuridica inserisce l'email
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email "personaGiuridica"
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia presente
    And Logout da portale persona giuridica
