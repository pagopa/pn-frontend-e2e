Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_Inserimento_Pec_Sbagliata_PF
  @addressBook1
  @NRT_VALIDATION
  Scenario:PN - PF Si inserisce una pec con caratteri speciali e si verifica che si evidenzia l'errore - homepage recapiti
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti

    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email
    And Attesa 1 secondi

    When Click Inizia
    And Click Bottone "Inserisci PEC"
    And Si inserisce la "pec" con Caratteri Speciali
