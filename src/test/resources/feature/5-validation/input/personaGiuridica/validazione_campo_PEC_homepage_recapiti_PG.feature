Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_Inserimento_Pec_Sbagliata_PG
  @addressBook2
  @NRT_VALIDATION
  Scenario:PN - PG Si inserisce una pec con caratteri speciali e si verifica che si evidenzia l'errore - homepage recapiti
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi

   #    Precondizione
    When Click Inizia
    And Click Insirisci Pec
    And Si inserisce la "pec" con Caratteri Speciali
