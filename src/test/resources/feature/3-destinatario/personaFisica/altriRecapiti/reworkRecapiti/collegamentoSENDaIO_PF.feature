Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_CollegamentoSENDaIO_PF
  @addressBook1

  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_2] - Attivazione Domicilio Digitale SERCQ SEND - Collegamento SEND ad IO
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva "domicilio digitale"
    # And Nella pagina I Tuoi Recapiti si controlla che non ci sia una email di cortesia impostata
    # And Nella pagina I Tuoi Recapiti si controlla che IO non sia integrato
    And Si clicca su 'Collega SEND a IO'
    # Then Nella pagina I Tuoi Recapiti si controlla che non ci sia una email di cortesia impostata