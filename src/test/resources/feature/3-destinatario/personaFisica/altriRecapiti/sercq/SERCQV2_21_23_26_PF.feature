Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_V2_21_23_26_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQV2_21_23_26_PF]
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Refresh pagina
    When Click Inizia
#   Configurazione domicilio digitale - Fase 2: Scenario 22 (Attivazione personalizzati PEC e SEND con DD attivo tramite PEC, e email attivo come recapito di cortesia)
    And Click Bottone "Inserisci PEC"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Inserisci PEC"
    And Click Attiva domicilio digitale PEC
    And Verifica  Indirizzo pec non valido
    And Spuntare checkbox privacy
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    And Click Attiva domicilio digitale PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1pf@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Attesa 1 secondi
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Bottone Conferma Modifica Recapito
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #Configurazione domicilio digitale - Fase 2: Scenario 24 (modifica email con DD personalizzato per ente)
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2pf@pec.it"
    And Click Bottone conferma Pop-up
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #Configurazione domicilio digitale - Fase 2: Scenario 23 (disattivazione email con DD personalizzato per ente)
    And Click su bottone Disattiva per il recapito mail
    And Click Annulla
    And Click su bottone Disattiva per il recapito mail
    And Click su bottone Conferma per la disattivazione del recapito mail
#   Configurazione domicilio digitale - Fase 2: Scenario 26 (Disattivazione recapito personalizzato SEND)
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Click Elimina personalizzati per ente
    And Click Annulla
    And Attesa 2 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica Da Attivare Domicilio digitale