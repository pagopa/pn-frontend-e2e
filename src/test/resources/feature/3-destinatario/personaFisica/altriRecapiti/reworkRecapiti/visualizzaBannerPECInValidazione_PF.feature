Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_ValidazionePEC_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_68] Visualizza banner - PEC personalizzati per ente
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Clicca tasto Accedi OneTrust PG e PF
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    # Attivazione PEC generale
    When Click Inizia
    And Attesa 1 secondi
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Click Lo Faro piu tardi
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    And Attesa 1 secondi
    When Verifica Attivazione Domicilio digitale
    # Creazione PEC per ente
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    And Si visualizza correttamente il banner di PEC in validazione "Agenzia delle Entrate"