Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneRecapitiInserimentoEmail_PF
  @addressBook1
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_28_29_27] Attivazione Recapiti Inserimento Email PF

#   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

##  REWORK_DOMICILIO_DIGITALE_PF_28
#inserisce email e click su Avvisami via email
    When Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up Annulla
    And Verifica Pagina "I tuoi recapiti"
    And Verifica Pagina "Il tuo indirizzo email"
##  REWORK_DOMICILIO_DIGITALE_PF_29
    And Aspetta 1 secondi
    When Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Cliccare sul bottone Annulla
    And Verifica Pagina "I tuoi recapiti"
    And Verifica Pagina "Il tuo indirizzo email"
    ##  REWORK_DOMICILIO_DIGITALE_PF_27
    And Aspetta 1 secondi
    When Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    And Verifica Pagina "ti avvisiamo con una email all"