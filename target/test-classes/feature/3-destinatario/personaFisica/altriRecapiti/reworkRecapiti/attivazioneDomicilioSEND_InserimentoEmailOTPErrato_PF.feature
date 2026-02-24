Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_inserimentoMailOTPErrato_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_70] La persona giuridica loggata inserisce un OTP sbagliato email
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Attesa 1 secondi
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    # Creazione Email
    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla