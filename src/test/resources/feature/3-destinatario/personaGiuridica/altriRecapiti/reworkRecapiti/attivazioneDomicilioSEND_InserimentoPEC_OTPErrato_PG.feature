Feature:La persona giuridica inserisce una OTP sbagliato PEC

  @TestSuite
  @TA_inserimentoPECOTPErrato_PG
  @addressBook2
  @TA_ON
  #  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_69] La persona giuridica loggata inserisce un OTP sbagliato PEC
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    # Attivazione PEC
    And Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica Pagina "Usa una PEC come domicilio digitale"
    And Verifica Pagina "Inserisci la tua PEC"
    And Verifica Pagina "Quando un ente invia una notifica su SEND alla tua impresa"
    And Verifica Pagina "Indirizzo PEC"
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla

