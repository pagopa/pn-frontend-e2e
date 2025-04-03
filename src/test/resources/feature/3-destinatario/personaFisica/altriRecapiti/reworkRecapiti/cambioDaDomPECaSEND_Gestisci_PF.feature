Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_43_47_PF
  @addressBook1
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_43_47] Attivazione Domicilio Digitale SEND - Inserisci Modifica PEC PF
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"


    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 1 secondi
 ##  REWORK_DOMICILIO_DIGITALE_PG_43
    When Click Bottone Gestisci
    And Click Bottone "Trasferisci su SEND"

    When Verifica Pagina "Trasferisci il domicilio digitale sulla piattaforma SEND"
    And Verifica Pagina "Come funziona"
    ##  REWORK_DOMICILIO_DIGITALE_PG_47
    And Click Bottone Esci PF
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "Il tuo indirizzo email"