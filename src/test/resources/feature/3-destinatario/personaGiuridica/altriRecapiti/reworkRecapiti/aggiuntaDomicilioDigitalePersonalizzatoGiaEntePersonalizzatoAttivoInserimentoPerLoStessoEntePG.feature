Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_75_72_73_76_74_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_75_72_73_76_74] Aggiunta domicilio digitale personalizzato (principale PEC) - Già ente personalizzato attivo - Inserimento per lo stesso ente PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica e Disattiva Personalizzati per Ente
    And Aspetta 1 secondi
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"


    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
     #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_75
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Verifica Pagina "Codice assente o incompleto"
    #  FINE REWORK_DOMICILIO_DIGITALE_PG_75
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
   #  FINE REWORK_DOMICILIO_DIGITALE_PG_72_73
    And Verifica Pagina "Validazione PEC in corso"

    And Aspetta 1 secondi

    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1@pec.it"
#    And Attesa 1 secondi
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
#    When Verifica Pagina "domicilio digitale"
    And Click Torna ai tuoi recapiti

    #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_76
    And Verifica Pagina "Validazione PEC in corso"
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Verifica Pagina "possibile associare il domicilio digitale SEND"
    And Click Bottone Esci PG
    And Aspetta 2 secondi

##  REWORK_DOMICILIO_DIGITALE_PG_74
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2@pec.it"
    And Verifica Pagina "Conferma modifica recapito"
    And Click Bottone Conferma Modifica Recapito

    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    When Verifica Pagina "domicilio digitale"
    And Click Torna ai tuoi recapiti
    And Aspetta 1 secondi
    And Verifica Pagina "prova2@pec.it"













