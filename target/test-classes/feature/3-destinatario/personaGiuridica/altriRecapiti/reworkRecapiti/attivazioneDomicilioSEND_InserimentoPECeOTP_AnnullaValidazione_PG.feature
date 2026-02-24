Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_AttivazioneDomicilioDigitaleSEND_InserisciPEC_OTP_PG
#  @addressBook2
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_14_18] Attivazione Domicilio Digitale SEND - Inserimento PEC e OTP annulla Validazione PG

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Insirisci Pec
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"

    And Verifica Pagina "Validazione PEC in corso"

    When Click annulla Validazione
    And Click Bottone conferma Pop-up
    Then Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Pagina "Inizia"
