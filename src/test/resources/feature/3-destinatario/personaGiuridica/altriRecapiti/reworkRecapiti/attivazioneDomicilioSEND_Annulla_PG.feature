Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_AttivazioneDomicilioDigitaleSEND_Annulla_PG
  @addressBook2
  @TA_REWORK_RECAPITI_ON
  @NRT_Blocco_1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_3] Attivazione Domicilio Digitale SEND - Annulla PG

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    When Click Inizia
    And Click Annulla
    And Attesa 1 secondi
    Then Verifica Da Attivare Domicilio digitale
