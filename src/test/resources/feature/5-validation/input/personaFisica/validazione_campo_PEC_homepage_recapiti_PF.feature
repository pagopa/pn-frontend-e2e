Feature: Rework della pagina dei contatti

  @TA_Inserimento_Pec_Sbagliata_Personalizza_tuo_domicilio_digitale_per_ente_mittente_PF
  @addressBook1
  @NRT_VALIDATION
  Scenario:PN - PF Inserimento Pec Sbagliata Personalizza tuo domicilio digitale per ente mittente PF
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

#    Scenario: 33
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Verifica Indirizzi "pec" Non Validi Con Caratteri Speciali per "ente"
