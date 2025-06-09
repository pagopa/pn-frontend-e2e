Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_63_64_PF
  @addressBook1
  @TA_ON
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_63_64_] Domicilio digitale SEND per ente personalizzato - Attiva Disattiva PG
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
    And Attesa 1 secondi
    When Click Inizia
    And Click Insirisci Pec
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
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
