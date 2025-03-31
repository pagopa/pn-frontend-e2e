Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_59_60_57_58_62_61_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_59_60_57_58_62_61] domicilioDigitalePEC_GestisciIndietroEsciInseriscoOTPErroreElimina PG
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


#  REWORK_DOMICILIO_DIGITALE_PG_59
    When Click Bottone Gestisci
    And Click Bottone "Indietro"
    And Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_60
    When Click Bottone Gestisci
    And Click Bottone Esci PG
    And Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_57
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
##  REWORK_DOMICILIO_DIGITALE_PG_58
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    When Verifica Pagina "Hai aggiornato il domicilio digitale della tua impresa"
    And Click Torna ai tuoi recapiti
    And Aspetta 2 secondi
##  REWORK_DOMICILIO_DIGITALE_PG_62
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Verifica presenza messaggio
    And Click Bottone Esci PG
    And Attesa 1 secondi
    And Click Elimina personalizzati per ente
    And Aspetta 1 secondi
    And Verifica Assenza Sezione Personalizzati Per Ente









