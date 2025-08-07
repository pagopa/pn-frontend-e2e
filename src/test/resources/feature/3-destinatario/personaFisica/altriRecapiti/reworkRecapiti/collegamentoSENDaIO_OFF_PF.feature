Feature: Rework della pagina dei contatti

#  @TestSuite_UAT_OFF
  @TA_CollegamentoSENDaIO_PF_OFF
  @addressBook1
  @TA_REWORK_RECAPITI_UAT_OFF
  @NRT_Blocco_2_OFF

  Scenario: [OFF_REWORK_DOMICILIO_DIGITALE_PF_91] - Attivazione Domicilio Digitale SERCQ SEND - Collegamento SEND ad IO - Feature Flag spento
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
##  REWORK_DOMICILIO_DIGITALE_PF_91
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si controlla che IO sia attivo
## Reset recapiti UAT
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
