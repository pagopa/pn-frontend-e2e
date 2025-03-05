Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciPEC_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_4] Attivazione Domicilio Digitale SEND Annulla PG - I tuoi Recapiti

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    Then Verifica Pagina "Usa una PEC come domicilio digitale"
    And Verifica Pagina "Inserisci la tua PEC"
    And Verifica Pagina "Quando un ente invia una notifica su SEND alla tua impresa"
    And Verifica Pagina "Indirizzo PEC"

