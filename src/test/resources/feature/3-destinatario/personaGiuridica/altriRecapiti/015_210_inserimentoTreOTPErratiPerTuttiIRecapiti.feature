Feature: La persona giuridica inserisce l'OTP errato 3 volte per i campi PEC, email di cortesia e numero di cellulare

  @TestSuite
  @TA_inserimentoTreOTPErratiPerTuttiIRecapiti
  @PG
  @recapitiPG

  Scenario: PN - 9709 - La persona giuridica inserisce l'OTP errato 3 volte per i campi PEC, email di cortesia e numero di cellulare
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce l'indirizzo della PEC "prova@test.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Si inserisce il codice OTP errato "11111" per tre volte e si controlla il messaggio di errore
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia una email di cortesia impostata
    And Si inserisce l'email di cortesia "prova@test.it" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma del disclaimer
    And Si inserisce il codice OTP errato "11111" per tre volte e si controlla il messaggio di errore
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia un numero di cellulare di cortesia impostato
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3334545889" e si clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma del disclaimer
    And Si inserisce il codice OTP errato "11111" per tre volte e si controlla il messaggio di errore
    And Logout da portale persona giuridica