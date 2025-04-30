Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_InserisciEmailTastoEsci_PF
  @addressBook1
  @TA_ON
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_8_78] Attivazione Domicilio Digitale SEND - Inserimento Email ESCI PF

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
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
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone Esci PF
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "app IO"
    And Verifica Pagina "Il tuo indirizzo email"
#REWORK_DOMICILIO_DIGITALE_PF_78
    When Click Inizia
    And Verifica Pagina "Come funziona"
    And Click Attiva
    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Click Bottone Esci PF
    And Verifica Pagina "Non rischiare di leggere in ritardo le tue notifiche"
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti