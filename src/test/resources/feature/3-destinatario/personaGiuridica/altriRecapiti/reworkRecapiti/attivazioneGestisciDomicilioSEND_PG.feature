Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneGestisciDomicilioDigitaleSEND_PG
  @addressBook2
  @TA_ON
    @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_1_20_21_22_23_26_24_25] Attivazione Gestisci Domicilio Digitale SEND PG - I tuoi Recapiti
   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
   And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
   And Attesa 1 secondi
    And Verifica e Disattiva email
   And Attesa 1 secondi
    When Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale
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
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
##  REWORK_DOMICILIO_DIGITALE_PF_25
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale