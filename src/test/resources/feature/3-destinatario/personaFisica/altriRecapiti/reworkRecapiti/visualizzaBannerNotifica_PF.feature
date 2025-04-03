Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_VisualizzaBannerNotifica_PF
  @addressBook1
  @NRT
  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_65_66_67] - Visualizza banner - Notifica/I tuoi dati
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
    And Nella pagina I Tuoi Recapiti si controlla che IO non sia attivato
    And Attesa 1 secondi
    And Click Inizia
    And Click Attiva
    And Click Non ora

    Then Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Attesa 1 secondi
    And Si visualizza correttamente la Pagina Notifiche persona fisica
    And Si visualizza correttamente il banner di recapito di cortesia mancante
    And La persona fisica clicca sulla prima notifica restituita
    And Si visualizza correttamente il banner di recapito di cortesia mancante
    And La persona fisica seleziona la voce I tuoi dati
    And Si visualizza correttamente il banner di recapito di cortesia mancante