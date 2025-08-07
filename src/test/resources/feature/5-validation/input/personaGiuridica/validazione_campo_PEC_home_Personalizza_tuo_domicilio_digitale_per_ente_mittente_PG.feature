Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_Inserimento_Pec_Sbagliata_Personalizza_tuo_domicilio_digitale_per_ente_mittente_PG
  @addressBook2
  @NRT_VALIDATION
  Scenario:PN - PG Si inserisce una pec con caratteri speciali e si verifica che si evidenzia l'errore - homepage Personalizza_tuo_domicilio_digitale_per_ente_mittente
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


    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"


    And Si inserisce "pec" con Caratteri Speciali Personalizza il tuo domicilio digitale per ente mittente
