Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_QA_7299_QA_7298_QA_7297_validation_bug_PF
  @addressBook1
  @TA_REWORK_RECAPITI_ON

  Scenario:[QA_7299_QA_7298_QA_7297_validation_bug_PF]
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Click Notifiche
    And Click Bottone Inizia nel Banner
    And Click Annulla Servizio Notifiche Digitali
    And Entro dentro la prima notifica
    And Click Bottone Inizia nel Banner
    And Click Annulla Servizio Notifiche Digitali
    And Click I Tuoi Dati
    And Click Bottone Inizia nel Banner
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    And Attesa 1 secondi
    When Verifica Attivazione Domicilio digitale
