Feature: la persona giuridica inserisce l'OTP numero di telefono  errato

  @TestSuite
  @PG
  @TA_inserimentoOTPErratoCellularePG
  @recapitiPG

  Scenario: PN-9158-A66 - La persona giuridica inserisce l'OTP numero di telefono errato
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PG "personaGiuridica" e clicca sul bottone avvisami via SMS
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella pagina I Tuoi Recapiti clicca sul bottone conferma
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
    And Logout da portale persona giuridica