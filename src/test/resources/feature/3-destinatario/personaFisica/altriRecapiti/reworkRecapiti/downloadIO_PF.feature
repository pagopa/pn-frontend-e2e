Feature: Rework della pagina dei contatti

#  @TestSuite_UAT_ON
#  @TA_DownloadIO_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_UAT_ON

  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_37] - Attivazione Domicilio Digitale SERCQ SEND - Scaricamento app IO
    Given Login Page persona fisica test viene visualizzata
    #Questo test in UAT richiede un account non abilitato a IO
    Given Login con persona fisica input
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Clicca tasto Accedi OneTrust PG e PF
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Aspetta 1 secondi
    And Click Scarica app IO
