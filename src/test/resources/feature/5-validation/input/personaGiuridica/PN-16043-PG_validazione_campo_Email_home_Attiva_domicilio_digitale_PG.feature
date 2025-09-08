Feature: PG Si inserisce un email con caratteri speciali e si verifica che si evidenzia l'errore - home Attiva domicilio digitale

#  @TestSuite_ON
  @TA_Inserimento_Email_Sbagliata_Domicilio_digitale_PG
  @addressBook2
  @NRT_VALIDATION
  Scenario:PN-16043-PG - PG Si inserisce un email con caratteri speciali e si verifica che si evidenzia l'errore - home Attiva domicilio digitale
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi

    When Click Inizia
    And Click Continua

    And Si inserisce "email" con Caratteri Speciali Home Page
