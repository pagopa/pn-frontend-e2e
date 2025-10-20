Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_8_1_12_14_16_20_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_8_1_12_14_16_20_PF]
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
#   SERCQ - Fase 2: Scenario 1
#   & Configurazione domicilio digitale - Fase 2: Scenario 14 (Attivazione SERCQ solo tramite email)
    When Click Inizia
    And Verifica della pagina Attiva domicilio digitale su SEND per PF
    And Verifica testo nella pagina "Hai una PEC?"
    And Verifica presenza bottone "Inserisci PEC"
    And Click Continua
    And Verifica della pagina La tua email per ricevere avvisi sulle notifiche SEND
    And Click Aggiungi email
    And Verifica  Indirizzo email non valido
    And Click Continua Tab Inserisci un recapito
    And Verifica della presenza della modale Importanza aggiunta contatti
    And Click Ok ho capito Recapiti
#    Scenario:8
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup
    And Click Aggiungi email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
#   SERCQ - Fase 2: Scenario 1
#   & Configurazione domicilio digitale - Fase 2: Scenario 14 (Attivazione SERCQ solo tramite email)
    And Click Continua Tab Inserisci un recapito
    And Verifica della pagina Stai attivando il tuo domicilio digitale su SEND
    And Click Attiva domicilio digitale
    And Verifica presenza Campo obbligatorio
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Verifica della TYP Hai attivato il tuo domicilio digitale su SEND
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
  #    Scenario:12
    And Click Modifica Email
    And Click Annulla
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PF e clicca su Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della nuova Email tramite request method
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata
#    Scenario:14
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    When Click Inizia
    And Click Continua
    And Click Modifica Email
    And Click Annulla
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email "emailprovapf@test.it" del PF e clicca su Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "emailprovapf@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata "emailprovapf@test.it"
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    Scenario:16
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    When Click Inizia
    And Click Continua
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla
    And Verifica e Disattiva email
#   SERCQ Fase 2: Scenario 20
    When Click Inizia
    And Click Continua
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva domicilio digitale "Annulla"
    And Verifica e Disattiva domicilio digitale "Conferma"
#   Configurazione domicilio digitale - Fase 2: Scenario 17 (disattivazione email con DD attivo)
#   Precondizioni
    When Click Inizia
    And Click Continua
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti

    And Click su bottone Disattiva per il recapito mail
    And Click Annulla
    And Verifica e Disattiva email
#   Configurazione domicilio digitale - Fase 2: Scenario 26 (disattivazione email con DD attivo e PEC personalizzato)
#  Precondizioni
    When Click Inizia
    And Click Continua

    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email

    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1pf@pec.it"
    And Spuntare checkbox privacy
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Click su bottone Disattiva per il recapito mail
    And Verifica pop-up Non è possibile disattivare l'email
    And Si chiude pop-up Non è possibile disattivare l'email
#   SERCQ Fase 2: Scenario 20 (precondizione)
    And Verifica ed Elimina personalizzati per ente
#   SERCQ Fase 2: Scenario 20
#   & Configurazione domicilio digitale - Fase 2: Scenario 19 (Disattivazione Domicilio Digitale SEND)
    And Disattiva domicilio digitale e Annulla
    And Verifica e Disattiva domicilio digitale "Conferma"

   #Configurazione domicilio digitale - Fase 2: Scenario 16 (disattivazione email senza DD attivo)
    When Click Inizia
    And Click Continua
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla
    And Click su bottone Disattiva per il recapito mail
    And Click Annulla
    And Verifica e Disattiva email