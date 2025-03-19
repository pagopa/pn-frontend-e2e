Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_49_50_53_54_51_52_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_49_50_53_54_51_52] Cambio Domicilio Digitale PEC Per Ente Personalizzato PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email

    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Bottone "Inserisci PEC"


    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Aspetta 1 secondi

    And Si inserisce l'email della "personaGiuridiche" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridiche"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email

##  REWORK_DOMICILIO_DIGITALE_PG_49
    When Click Bottone Gestisci
    And Click Bottone "Trasferisci su SEND"
    And Click Attiva
    When Verifica Pagina "Hai aggiornato il domicilio digitale della tua impresa"
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_50
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Verifica Pagina "Personalizza il tuo domicilio digitale per ente mittente"
    And Verifica Pagina "ente e il recapito da associare"
##  REWORK_DOMICILIO_DIGITALE_PG_53
    When Click Bottone "Indietro"
    And Verifica Pagina "domicilio digitale"
    And Verifica Pagina "La piattaforma SEND"
##  REWORK_DOMICILIO_DIGITALE_PG_54
    When Click Bottone "Personalizza per ente"
    And Click Bottone Esci PG
    And Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_51
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate - Riscossione"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
##  REWORK_DOMICILIO_DIGITALE_PG_52
    When Verifica Pagina "Hai aggiornato il domicilio digitale della tua impresa"
    And Click Torna ai tuoi recapiti
    And Verifica Pagina "Validazione PEC in corso"



