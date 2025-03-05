Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserirePEC_PF
  @addressBook1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_4] Attivazione Domicilio Digitale SEND - Inserisci PEC PF
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

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica Pagina "Usa una PEC come domicilio digitale"
    And Verifica Pagina "Inserisci la tua PEC"
    And Verifica Pagina "Quando un ente invia una notifica su SEND"
    And Verifica Pagina "Indirizzo PEC"
