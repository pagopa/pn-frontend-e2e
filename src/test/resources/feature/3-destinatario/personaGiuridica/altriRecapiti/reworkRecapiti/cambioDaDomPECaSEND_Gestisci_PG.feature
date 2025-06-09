Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_43_47_PG
  @addressBook2
  @TA_ON
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_43_47] Cambio da dom PEC a SEND - Gestisci PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email

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

##  REWORK_DOMICILIO_DIGITALE_PG_43
    When Click Bottone Gestisci
    And Click Bottone "Trasferisci su SEND"

    When Verifica Pagina "Trasferisci il domicilio digitale sulla piattaforma SEND"
    And Verifica Pagina "Come funziona"
    ##  REWORK_DOMICILIO_DIGITALE_PG_47
    And Click Bottone Esci PG
    Then Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Pagina "Indirizzo email aziendale"



