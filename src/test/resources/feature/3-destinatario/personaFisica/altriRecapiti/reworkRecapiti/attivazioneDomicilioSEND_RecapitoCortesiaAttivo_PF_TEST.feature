Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_attivazioneDomicilioSEND_RecapitoCortesiaAttivo_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_38_TEST] Attivazione Domicilio Digitale SEND PF - Recapiti di cortesia presenti - Ambiente TEST

    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Aspetta 2 secondi
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    When Click Inizia
    And Click Attiva
    Then Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale