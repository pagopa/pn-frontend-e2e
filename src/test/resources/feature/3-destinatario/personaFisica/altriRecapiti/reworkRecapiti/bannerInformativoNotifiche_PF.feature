Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_BanneInformativoNotifiche_PF
  @addressBook1
  @TA_REWORK_RECAPITI_ON

  Scenario: [REWORK_DOMICILIO_DIGITALE_PF_15_16] - Attivazione Domicilio Digitale SEND - Banner informativo link consegnata PF
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
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Click Bottone Notifica
    And Click Bottone "Inizia"

    When Verifica Pagina "Attiva domicilio digitale"
    And Verifica Pagina "Inserisci PEC"
    And Verifica Pagina "Come funziona"
    And Verifica Pagina "Accedi alla notifica"

    When Click link consegnata
    And Verifica Pagina "Valore giuridico della notifica sul domicilio digitale SEND"
    And Verifica Pagina "Se scegli SEND come domicilio digitale"

    And Click Bottone "ho capito"
    Then Verifica Pagina "Attiva domicilio digitale"
    And Verifica Pagina "Inserisci PEC"
    And Verifica Pagina "Come funziona"
    And Verifica Pagina "Accedi alla notifica"