Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneGestisciDomicilioDigitaleSEND_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_1_20_21_22_23_26_24_25] Attivazione Gestisci Domicilio Digitale SEND PG - I tuoi Recapiti


   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard




    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    When Click Inizia
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PF_20
    When Nella pagina I Tuoi Recapiti verifica assenza bottone Modifica PEC
##  REWORK_DOMICILIO_DIGITALE_PF_21
    When Click Bottone Gestisci
    And Verifica Pagina "Gestisci domicilio digitale"
    And Verifica Pagina "La piattaforma SEND"
##  REWORK_DOMICILIO_DIGITALE_PF_22
    When Click Bottone "Trasferisci su una PEC"
    And Verifica Pagina "Trasferisci il domicilio digitale su una PEC"
    And Verifica Pagina "Inserisci la tua PEC"
##  REWORK_DOMICILIO_DIGITALE_PF_23
    When Click Bottone "Indietro"
    And Verifica Pagina "Gestisci domicilio digitale"
    And Verifica Pagina "La piattaforma SEND"
    ##  REWORK_DOMICILIO_DIGITALE_PF_26
    When Click Bottone "Trasferisci su una PEC"
    And Click Bottone Conferma in Trasferisci il domicilio digitale su una PEC
    And Verifica Pagina "Indirizzo PEC non valido"
##  REWORK_DOMICILIO_DIGITALE_PF_24
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
##  REWORK_DOMICILIO_DIGITALE_PF_25
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Verifica Pagina "Hai aggiornato il domicilio digitale della tua impresa"