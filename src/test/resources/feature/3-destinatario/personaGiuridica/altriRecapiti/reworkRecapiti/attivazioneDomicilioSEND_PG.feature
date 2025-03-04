Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_1]Attivazione Domicilio Digitale SEND PG - I tuoi Recapiti

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    When Click Inizia
    And Click Attiva
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale della tua impresa