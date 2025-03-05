Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_TastoEsci_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_8] Attivazione Domicilio Digitale SEND - ESCI  PF
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Bottone Esci PG
    Then Verifica Pagina "Il domicilio digitale della tua impresa"
    And Verifica Pagina "Indirizzo email aziendale"

