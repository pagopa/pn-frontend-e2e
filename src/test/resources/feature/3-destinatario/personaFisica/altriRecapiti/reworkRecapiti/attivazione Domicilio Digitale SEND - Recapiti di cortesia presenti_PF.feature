Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_PF_17
  @addressBook1
  @TA_NRT_UAT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_17] Attivazione Domicilio Digitale SEND - Recapiti di cortesia presenti PF

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica input
#      | user         | pluto-ta               |
#      | pwd          | password123            |
#      | name         | Rossi                  |
#      | familyName   | Pluto                  |
#      | fiscalNumber | TINIT-AAAAAA00A00A000B |
      | user         | pippo-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pippo                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000A |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva app IO
    And Aspetta 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Aspetta 1 secondi

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 1 secondi
##  REWORK_DOMICILIO_DIGITALE_PF_44
    When Click Bottone Gestisci
    And Click Bottone "Trasferisci su SEND"
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    And Verifica presenza bottone Attiva SEND su IO



#
