Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_PF
  @addressBook1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_1] Attivazione Domicilio Digitale SEND PF - I tuoi Recapiti
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti


    #    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    When Click Inizia
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale della tua impresa
