Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_33_37_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_33_37_PF]
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
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
    And Refresh pagina
#    Precondizione
    When Click Inizia
    And Click Insirisci Pec
    And Spuntare checkbox privacy
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Click Aggiungi email
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Refresh pagina
#   SERCQ - Fase 2: Scenario 33
#   & Configurazione domicilio digitale - Fase 2: Scenario 22 (Aggiunta recapiti personalizzati SEND e PEC solo tramite email)
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Personalizza per ente"
    And Verifica della pagina Personalizza il tuo domicilio digitale per ente mittente per PF
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Verifica campo obbligatorio Ente e Tipologia
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1pf@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Verifica della pagina Hai aggiornato il tuo domicilio digitale per PF
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    Scenario: 33
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate - Riscossione"
    And Seleziona Tipologia "Domicilio digitale SEND"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Verifica presenza Campo obbligatorio
    And Spuntare checkbox privacy
    And Attesa 1 secondi
    And Non si visualizzano correttamente i pulsanti modifica, elimina e non è possibile modificare l'email
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#   SERCQ - Fase 2: Scenario: 37
#   & Configurazione domicilio digitale - Fase 2: Scenario 27 (Disattivazione DD SEND personalizzato)
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Si chiude pop-up Impossibile disattivare il Domicilio Digitale
    And Click su Disattiva Personalizzati per Ente e Annulla
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Annulla"
    And Attesa 2 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica Da Attivare Domicilio digitale