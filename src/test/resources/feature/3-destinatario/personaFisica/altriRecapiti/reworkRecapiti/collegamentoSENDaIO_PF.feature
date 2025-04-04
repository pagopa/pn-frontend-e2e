Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_CollegamentoSENDaIO_PF
  @addressBook1
  @TA_NRT_UAT
  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_2_33_34_35_36] - Attivazione Domicilio Digitale SERCQ SEND - Collegamento SEND ad IO
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica input
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
##  REWORK_DOMICILIO_DIGITALE_PG_2_36
    And Si clicca su 'Attiva SEND su IO'
    And Click Annulla
##  REWORK_DOMICILIO_DIGITALE_PG_35
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Verifica Pagina "la ricevi direttamente in app e puoi pagare eventuali spese."
##  REWORK_DOMICILIO_DIGITALE_PG_34
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Click Lo Faro piu tardi
    And Attesa 1 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    And Verifica Attivazione Domicilio digitale
    And Si clicca su 'Attiva SEND su IO'
    And Click Annulla
##  REWORK_DOMICILIO_DIGITALE_PG_33
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Verifica Pagina "la ricevi direttamente in app e puoi pagare eventuali spese."
## Reset recapiti UAT
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
