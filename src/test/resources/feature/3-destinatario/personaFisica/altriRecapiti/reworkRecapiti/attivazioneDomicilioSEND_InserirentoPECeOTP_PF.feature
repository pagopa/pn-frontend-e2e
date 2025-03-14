Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciPEC_OTP_PF
  @addressBook1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_14_18] Attivazione Domicilio Digitale SEND - Inserimento PEC e OTP annulla Valutazione PF
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Attesa 2 secondi
    And Verifica e Disattiva "email"

    When Click Inizia
    And Click Insirisci Pec
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"

    And Verifica Pagina "Validazione PEC in corso"

    When Click annulla valutazione
    And Click Bottone conferma Pop-up
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "Inizia"