Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_AttivazioneGestisciDomicilioDigitaleSEND_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_1_20_21_22_23_26_24_25] Attivazione Gestisci Domicilio Digitale SEND PF -I tuoi Recapiti
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Verifica e Disattiva email
    When Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    When Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PF_20
    When Nella pagina I Tuoi Recapiti verifica assenza bottone Modifica PEC
##  REWORK_DOMICILIO_DIGITALE_PF_21
    When Click Bottone Gestisci
    And Si visualizza correttamente la pagina Gestisci Il Tuo Dominio Digitale
##  REWORK_DOMICILIO_DIGITALE_PF_22
    When Click Bottone "Trasferisci su una PEC"
    And Verifica Pagina "Trasferisci il domicilio digitale su una PEC"
    And Verifica Pagina "Inserisci la tua PEC"
##  REWORK_DOMICILIO_DIGITALE_PF_23
    When Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Si visualizza correttamente la pagina Gestisci Il Tuo Dominio Digitale
    ##  REWORK_DOMICILIO_DIGITALE_PF_26
    When Click Bottone "Trasferisci su una PEC"
    And Click Bottone Conferma in Trasferisci il domicilio digitale su una PEC
    And Si visualizza correttamente il messaggio di pec non valida
##  REWORK_DOMICILIO_DIGITALE_PF_24
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
##  REWORK_DOMICILIO_DIGITALE_PF_25
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale

