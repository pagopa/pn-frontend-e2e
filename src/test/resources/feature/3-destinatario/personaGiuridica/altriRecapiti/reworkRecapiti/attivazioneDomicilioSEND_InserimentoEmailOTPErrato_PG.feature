Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_inserimentoMailOTPErrato_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_70] La persona giuridica loggata inserisce un OTP sbagliato email
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    # Creazione Email
    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla