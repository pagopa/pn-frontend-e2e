Feature: Rework della pagina dei contatti

#  @TestSuite
  @TA_AttivazioneRecapitiVerificaCell_PF
  @addressBook1
  @TA_REWORK_RECAPITI_ON

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_31_32] Attivazione Recapiti Verifica CEll PF

    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

###  REWORK_DOMICILIO_DIGITALE_PF_31
    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
    And Si clicca sul bottone del pop-up Annulla
    And Verifica Pagina "Numero di cellulare"
    And Verifica Pagina "I tuoi recapiti"
###  REWORK_DOMICILIO_DIGITALE_PF_32
    And Aspetta 1 secondi
    When Click Bottone "Aggiungi un numero di cellulare"
    When Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
    And Si clicca sul bottone del pop-up ok ho capito
    And Cliccare sul bottone Annulla
    And Verifica Pagina "Il tuo indirizzo email"
    And Verifica Pagina "Indirizzo email"
