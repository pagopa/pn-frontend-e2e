Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_RecapitoCortesiaAttivo_PF_UAT
  @addressBook1
  @TA_NRT_UAT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_17_38_UAT] Attivazione Domicilio Digitale SEND PF - Recapiti di cortesia presenti - Ambiente UAT
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
#   Collegamento SEND a IO
    And Aspetta 2 secondi
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si controlla che IO sia attivo
#   Inserimento Email
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
#   Attivazione Domicilio Digitale
    When Click Inizia
    And Click Attiva
    Then Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale