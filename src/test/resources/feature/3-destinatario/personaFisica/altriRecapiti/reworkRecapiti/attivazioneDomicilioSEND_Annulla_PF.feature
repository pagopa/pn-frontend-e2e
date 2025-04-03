Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_Annulla_PF
  @addressBook1
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_3] Attivazione Domicilio Digitale SEND - Annulla PF

#   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

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
    And Verifica e Disattiva email
    When Click Inizia
    And Click Annulla
    Then Verifica Da Attivare Domicilio digitale
