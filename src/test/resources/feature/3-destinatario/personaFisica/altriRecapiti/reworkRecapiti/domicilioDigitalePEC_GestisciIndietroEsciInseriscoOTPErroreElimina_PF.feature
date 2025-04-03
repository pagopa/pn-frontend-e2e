Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_REWORK_DOMICILIO_DIGITALE_59_60_57_58_62_61_PF
  @addressBook1
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_59_60_57_58_62_61] Cambio Domicilio Digitale PEC Per Ente Personalizzato PF
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
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Insirisci Pec
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 1 secondi


   #  REWORK_DOMICILIO_DIGITALE_PG_59
    When Click Bottone Gestisci
    And Click Bottone "Indietro"
    And Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_60
    When Click Bottone Gestisci
    And Click Bottone Esci PF
    And Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Attivazione Domicilio digitale
#  REWORK_DOMICILIO_DIGITALE_PG_57
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova@pec.it"
    And Attesa 1 secondi
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
##  REWORK_DOMICILIO_DIGITALE_PG_58
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    When Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale
    And Click Torna ai tuoi recapiti
    And Aspetta 2 secondi
##  REWORK_DOMICILIO_DIGITALE_PG_62
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Seleziona Tipologia "Indirizzo PEC"
    And Verifica presenza messaggio
    And Click Bottone Esci PF
    And Attesa 1 secondi
    And Click Elimina personalizzati per ente
    And Aspetta 1 secondi
    And Verifica Assenza Sezione Personalizzati Per Ente