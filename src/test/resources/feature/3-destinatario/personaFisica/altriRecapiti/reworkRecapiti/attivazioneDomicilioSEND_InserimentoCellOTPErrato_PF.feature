Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_inserimentoCellOTPErrato_PF
  @addressBook1
  @TA_ON
  @NRT_ON
  @TA_REWORK_RECAPITI_ON
  @GestioneErrori
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_71] La persona giuridica loggata inserisce un OTP sbagliato cellulare
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
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    # Creazione Numero cellulare
    When Click Inizia
    And Click Attiva
    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PF e clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Attesa 2 secondi
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla