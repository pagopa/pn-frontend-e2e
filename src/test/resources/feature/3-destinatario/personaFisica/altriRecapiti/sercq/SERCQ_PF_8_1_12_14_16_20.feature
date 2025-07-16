Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_PF_8_1_12_14_16_20
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_PF_8_1_12_14_16_20] codice OTP errato
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    ##     verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
#    And Attesa 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale
#    And Attesa 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email

    When Click Inizia
    And Click Continua


    #    Scenario:1
    And Click Aggiungi email
    And Verifica  Indirizzo email non valido
    And Click Continua Tab Inserisci un recapito
    And Click Ok ho capito Recapiti
#    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
#    And Nella sezione altri recapiti si clicca sul bottone annulla di popup

#    Scenario:8
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup

#    Scenario:1
    And Click Continua Tab Inserisci un recapito
    And Click Attiva domicilio digitale
    And Verifica presenza Campo obbligatorio
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti

    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti





#   TODO da completare





#    And Click Bottone "Inserisci PEC"
#    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#     #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_75
#    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
#    And Verifica Pagina "Codice assente o incompleto"
#    #  FINE REWORK_DOMICILIO_DIGITALE_PG_75
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
#    #  FINE REWORK_DOMICILIO_DIGITALE_PG_72_73
#    And Verifica Pagina "Validazione PEC in corso"
#
#
##    And Aspetta 2 secondi
#    And Attesa 2 secondi
#    And Refresh pagina
#    When Click Bottone Gestisci
#    And Click Bottone "Personalizza per ente"
#    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
#    And Seleziona Tipologia "Indirizzo PEC"
#    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1@pec.it"
##    And Attesa 1 secondi
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1@pec.it" tramite chiamata request
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
##    When Verifica Pagina "domicilio digitale"
#    And Click Torna ai tuoi recapiti
#
#    #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_76
#    And Verifica Pagina "Validazione PEC in corso"
#    When Click Bottone Gestisci
#    And Click Bottone "Personalizza per ente"
#    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
#    And Seleziona Tipologia "Domicilio Digitale SEND"
#    And Verifica Pagina "possibile associare il domicilio digitale SEND"
#    And Click Bottone Esci PF
##    And Aspetta 2 secondi
#    And Attesa 2 secondi
#    And Refresh pagina
#
###  REWORK_DOMICILIO_DIGITALE_PG_74
#    When Click Bottone Gestisci
#    And Click Bottone "Personalizza per ente"
#    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
#    And Seleziona Tipologia "Indirizzo PEC"
#    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2@pec.it"
#    And Verifica Pagina "Conferma modifica recapito"
#    And Click Bottone Conferma Modifica Recapito
#
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2@pec.it" tramite chiamata request
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
#    When Verifica Pagina "domicilio digitale"
#    And Click Torna ai tuoi recapiti
##    And Aspetta 1 secondi
#    And Attesa 2 secondi
#    And Refresh pagina
#    And Verifica Pagina "prova2@pec.it"