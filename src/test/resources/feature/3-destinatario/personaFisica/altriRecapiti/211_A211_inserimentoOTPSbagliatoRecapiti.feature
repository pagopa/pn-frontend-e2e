Feature:La persona fisica inserisce una OTP sbagliato PEC

  @TestSuite
  @TA_inserimentoOTP3Volte
  @PF
  @recapitiPF

    #TEST SOLO IN HEADLESS
  Scenario: Recapiti - Messaggio di errore dopo 3 tentativi inserimento OTP
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "pec@prova.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email "email@test.it" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono PF "3409876543" e clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
    And Logout da portale persona fisica

