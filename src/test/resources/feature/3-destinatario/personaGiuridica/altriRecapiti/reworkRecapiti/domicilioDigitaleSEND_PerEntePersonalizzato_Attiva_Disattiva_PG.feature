Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_REWORK_DOMICILIO_DIGITALE_63_64_PG
  @addressBook2
  @TA_REWORK_RECAPITI_ON

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_63_64] Domicilio digitale SEND per ente personalizzato - Attiva Disattiva PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica ed Elimina personalizzati per ente
    And Aspetta 1 secondi
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"


    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Aspetta 1 secondi

##  REWORK_DOMICILIO_DIGITALE_PG_63
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Domicilio Digitale SEND"
    And Click Bottone Conferma Personalizza il tuo domicilio digitale per ente
    And Click Torna ai tuoi recapiti
#  REWORK_DOMICILIO_DIGITALE_PG_64
    Then Click Bottone Disattiva In domicilio digitale "domicilio digitale"
    And Verifica Pagina "devi prima disattivare i domicili digitali personalizzati"
    And Click Bottone "ho capito"
    And Verifica Pagina "domicilio digitale"













