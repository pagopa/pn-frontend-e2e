Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_V2_23_24_25_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQV2_23_24_25_PF]
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
#   Configurazione domicilio digitale - Fase 2: Scenario 23 (Attivazione personalizzato SEND con DD attivo tramite PEC, senza recapito di cortesia email)
    And Click Bottone "Inserisci PEC"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Inserisci PEC"
    And Click Attiva domicilio digitale PEC
    And Verifica della pagina Usa una PEC come domicilio digitale per PF
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
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Personalizza per ente"
    And Verifica della pagina Personalizza il tuo domicilio digitale per ente mittente per PF
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Click Aggiungi email
    And Verifica  Indirizzo email non valido
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Verifica presenza Campo obbligatorio
    And Attesa 1 secondi
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Spuntare checkbox privacy
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Attesa 1 secondi
    And Verifica della pagina Hai aggiornato il tuo domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #Configurazione domicilio digitale - Fase 2: Scenario 25 (modifica email con DD personalizzato per ente)
#   Precondizione
    And Refresh pagina
    And Verifica e Disattiva email
    And Attesa 10 secondi
    And Refresh pagina
#   Scenario 24
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Spuntare checkbox privacy
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email "emailprovapf@test.it" del PF e clicca su Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "emailprovapf@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata "emailprovapf@test.it"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #Configurazione domicilio digitale - Fase 2: Scenario 24 (disattivazione recapito cortesia email con DD personalizzato per ente)
    And Verifica e Disattiva email