Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_RecapitoCortesiaAttivo_PF_UAT
  @addressBook1
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_38_UAT] Attivazione Domicilio Digitale SEND PF - Recapiti di cortesia presenti - Ambiente UAT

    And Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva "domicilio digitale"
    And Aspetta 2 secondi
    And Verifica e Disattiva "app IO"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"
#   Collegamento SEND a IO
    And Aspetta 2 secondi
    And Verifica e Disattiva "app IO"
    And Click Inizia
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
#   Inserimento Email
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
#   Attivazione Domicilio Digitale
    When Click Inizia
    And Click Attiva
    Then Verifica Pagina "Hai attivato il tuo domicilio digitale"
    And Verifica Pagina "Torna ai tuoi recapiti"