Feature:La persona fisica inserisce una OTP sbagliato PEC

#  @TestSuite_ON
#  @TA_inserimentoPECOTPErrato_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_69] La persona fisica loggata inserisce un OTP sbagliato PEC
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Clicca tasto Accedi OneTrust PG e PF
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    # Attivazione PEC
    And Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica testo nella pagina "Usa una PEC come domicilio digitale"
    And Verifica testo nella pagina "Inserisci la tua PEC"
    And Verifica testo nella pagina "Quando un ente ti invia una notifica SEND"
    And Verifica testo nella pagina "Indirizzo PEC"
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla

