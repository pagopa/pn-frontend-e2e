Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_31_32_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_31_32_PG]
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email

    And Attesa 1 secondi
    And Refresh pagina





   #    Precondizione
    When Click Inizia
    And Click Continua
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Attesa 3 secondi
    And Refresh pagina

    #    Scenario: 31
    And Attesa 2 secondi
    And Click Bottone Gestisci
    And Click Bottone "Trasferisci su una PEC"
    When Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Trasferisci su una PEC"
    And Spuntare checkbox privacy
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Click Torna ai tuoi recapiti

    And Attesa 1 secondi
    And Refresh pagina

    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica testo nella pagina "pec@pec.pagopa.it"
    And Attesa 10 secondi
    And Refresh pagina
    And Verifica e Disattiva email
    And Refresh pagina
#   SERCQ - Fase 2: Scenario 32
#   & Configurazione domicilio digitale - Fase 2: Scenario 21 (Trasferimento DD da PEC a SEND solo tramite email)
    And Click Bottone Gestisci
    And Verifica della pagina Gestisci il domicilio digitale per PG
    And Click Bottone "Trasferisci su SEND"
    And Verifica della pagina Attiva domicilio digitale su SEND per PG
    And Verifica testo nella pagina "La piattaforma SEND sostituirà la PEC aziendale come domicilio digitale della tua impresa"
    And Click Continua
    And Click Aggiungi email
    And Verifica  Indirizzo email non valido
    And Click Continua Tab Inserisci un recapito
    And Click Ok ho capito Recapiti
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Verifica della pagina Stai attivando il domicilio digitale su SEND per PG
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Verifica della TYP Hai trasferito il domicilio digitale su SEND per PG
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti