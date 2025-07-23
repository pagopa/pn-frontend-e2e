Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_33_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_33_PF]
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
#    Given Login Page persona fisica test viene visualizzata
#    Given Login con persona fisica
#      | user         | cesare                 |
#      | pwd          | password123            |
#      | name         | Gaio Giulio            |
#      | familyName   | Cesare                 |
#      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    ##     verificare mancano pezzi inerente a SEND sull'appIO
#    And Verifica ed Elimina personalizzati per ente
##    And Aspetta 1 secondi
#    And Attesa 1 secondi
#    And Refresh pagina
#    And Verifica ed Elimina personalizzati per ente
##    And Attesa 1 secondi
#    And Attesa 1 secondi
#    And Refresh pagina
#    And Verifica e Disattiva domicilio digitale
##    And Attesa 1 secondi
#    And Attesa 1 secondi
#    And Refresh pagina
#    And Verifica e Disattiva email
#    And Verifica e Disattiva cellulare

##    Precondizione
#    When Click Inizia
#    And Click Insirisci Pec
#    And Spuntare checkbox privacy
#    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
#    And Click Torna ai tuoi recapiti
#    And Attesa 2 secondi
#    And Refresh pagina
#    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti




#    Scenario: 33
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Personalizza per ente"

    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente

    And Verifica campo obbligatorio Ente e Tipologia

    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Spuntare checkbox privacy

    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina

#    #    Scenario: 34
#    And Click Bottone Gestisci
#    And Click Bottone "Personalizza per ente"
#    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
##    And Seleziona Tipologia "Indirizzo PEC"
#    And Spuntare checkbox privacy
#    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2@pec.it"
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2@pec.it" tramite chiamata request
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
#    And Click Torna ai tuoi recapiti
#    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    And Attesa 2 secondi
#    And Refresh pagina
#    Then Verifica Pagina "prova2@pec.it"
#
#    #    Scenario: 35
#
#    And Click Modifica personalizzati per ente
#    And Modifica Pec personalizzati per Ente e conferma "pectest@pec.it"
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "pectest@pec.it" tramite chiamata request
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
#    And Si verifica se popup conferma presente
#    And Attesa 3 secondi
#    And Refresh pagina
#    And Verifica Pagina "pectest@pec.it"
#
#    #    Scenario: 36
#    When Click Elimina personalizzati per ente
#    And Attesa 2 secondi
#    And Refresh pagina
#    And Verifica Assenza Sezione Personalizzati Per Ente
#
#    #Scenario: 37
#    And Verifica e Disattiva domicilio digitale
#    And Attesa 2 secondi
#    And Refresh pagina
#    Then Verifica Da Attivare Domicilio digitale

#    Scenario: 33
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Bottone Conferma Modifica Recapito
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti