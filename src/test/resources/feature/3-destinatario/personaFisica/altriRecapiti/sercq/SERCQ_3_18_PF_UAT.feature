Feature: Rework della pagina dei contatti

#  @TestSuite_UAT_ON
  @TA_SERCQ_3_18_PF_UAT
  @addressBook1
  @TA_SERCQ_UAT_ON

  Scenario:[SERCQ_3_18_PF_UAT] Attivazione Domicilio Digitale SEND PF - Recapiti di cortesia presenti - Ambiente UAT
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Rimuovi da API tutti i recapiti per persona fisica se esistono
    And Verifica e Disattiva app IO
    And Refresh pagina
  #    Scenario: 3
    When Click Inizia
    And Click Continua
    And Click Continua senza collegare IO
    And Click Lo Faro piu tardi
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Si clicca su 'Collega SEND su IO'
    And Attesa 2 secondi
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Scollega SEND da IO in Attiva domicilio digitale su SEND
    And Click Scollega SEND da IO nel Pop-up Aggiungi i tuoi recapiti e importante
    And Attesa 1 secondi
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla

#      Scenario: 18
    And Si clicca su 'Attiva SEND su IO'
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si controlla che IO sia attivo
    And Disattiva app IO e Annulla

    And Verifica e Disattiva app IO
    And Nella pagina I Tuoi Recapiti si controlla che IO non sia attivato