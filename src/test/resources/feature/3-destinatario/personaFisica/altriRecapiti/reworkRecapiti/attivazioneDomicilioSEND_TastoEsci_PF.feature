Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_TastoEsci_PF
  @addressBook1
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_8_77_78] Attivazione Domicilio Digitale SEND - ESCI  PF

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Aspetta 2 secondi
    And Click Bottone "Esci"
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "app IO"
    And Verifica Pagina "Il tuo indirizzo email"
#REWORK_DOMICILIO_DIGITALE_PF_77-78
    When Click Inizia
    And Aspetta 2 secondi
    And Click Attiva
    And Click Bottone "Esci"
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "app IO"
    And Verifica Pagina "Il tuo indirizzo email"
