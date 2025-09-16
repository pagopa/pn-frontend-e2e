Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_33_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_33_37_PG]
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi







   #    Precondizione
    When Click Inizia
    And Click Insirisci Pec
    And Spuntare checkbox privacy
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Click Torna ai tuoi recapiti
    And Attesa 2 secondi
    And Refresh pagina
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti


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

#    Scenario: 33
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Attesa 1 secondi
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

 #    Precondizione Scenario: 37
    And Verifica e Disattiva Personalizzati per Ente

      #Scenario: 37
    And Verifica e Disattiva domicilio digitale "Annulla"
    And Attesa 2 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica Da Attivare Domicilio digitale