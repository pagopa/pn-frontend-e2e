Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_DisattivazioneSENDsuIO_PF
  @addressBook1
  @TA_NRT_UAT
  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_39_40] - Attivazione Domicilio Digitale SERCQ SEND - Disattivazione SEND su IO
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Aspetta 1 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
##  Inserimento recapito solo email (cellulare non ancora possibile)
    And Click Inizia
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    And Verifica Attivazione Domicilio digitale
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Aspetta 1 secondi
##  REWORK_DOMICILIO_DIGITALE_PG_40
    And Nella pagina I Tuoi Recapiti si preme sul bottone Disattiva dell'app IO
    And Click Annulla
    And Aspetta 1 secondi
##  REWORK_DOMICILIO_DIGITALE_PG_39
    And Verifica e Disattiva "app IO"
## Reset recapiti UAT
    And Verifica e Disattiva "domicilio digitale"
    And Attesa 1 secondi
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"