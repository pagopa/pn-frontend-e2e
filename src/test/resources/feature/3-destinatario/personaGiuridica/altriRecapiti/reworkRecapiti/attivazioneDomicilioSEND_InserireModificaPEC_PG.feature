Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciModificaPEC_PG
  @addressBook2
  @TA_ON
  #  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_4_19] Attivazione Domicilio Digitale SEND - Inserisci PEC e Modifica PEC  PG

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica Pagina "Usa una PEC come domicilio digitale"
    And Verifica Pagina "Inserisci la tua PEC"
    And Verifica Pagina "Quando un ente invia una notifica su SEND alla tua impresa"
    And Verifica Pagina "Indirizzo PEC"


    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Aspetta 2 secondi
## Modifica
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "provaa@pec.it"
    And Click Bottone Conferma per modifica PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "provaa@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente

    Then Verifica Pagina "provaa@pec.it"


