Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserireModificaPEC_PF
  @addressBook1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_4_19] Attivazione Domicilio Digitale SEND - Inserisci Modifica PEC PF
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
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica Pagina "Usa una PEC come domicilio digitale"
    And Verifica Pagina "Inserisci la tua PEC"
    And Verifica Pagina "Quando un ente ti invia una notifica SEND"
    And Verifica Pagina "Indirizzo PEC"


    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 2 secondi
  ## Modifica
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "prova@pec.it"
    And Click Bottone Conferma per modifica PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente

    Then Verifica Pagina "prova@pec.it"