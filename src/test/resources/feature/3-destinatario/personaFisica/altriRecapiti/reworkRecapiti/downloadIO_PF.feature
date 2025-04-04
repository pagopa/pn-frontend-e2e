Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_DownloadIO_PF
  @addressBook1
  @TA_NRT_UAT
  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_37] - Attivazione Domicilio Digitale SERCQ SEND - Scaricamento app IO
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Click Scarica app IO
