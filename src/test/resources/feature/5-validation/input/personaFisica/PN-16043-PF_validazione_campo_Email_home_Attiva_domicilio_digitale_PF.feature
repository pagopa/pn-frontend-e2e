Feature: PF Si inserisce un email con caratteri speciali e si verifica che si evidenzia l'errore - home Attiva domicilio digitale

#  @TestSuite_ON
  @TA_Inserimento_email_Sbagliata_Domicilio_digitale_PF
  @addressBook1
  @NRT_VALIDATION
  Scenario:PN-16043-PF - PF Si inserisce un email con caratteri speciali e si verifica che si evidenzia l'errore - home Attiva domicilio digitale
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
    And Click Continua

#    And Si inserisce "email" con Caratteri Speciali Home Page
    And Verifica Indirizzi "email" Non Validi Con Caratteri Speciali per "homepage"
