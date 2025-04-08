Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_VisualizzaBannerNotifica_PG
  @addressBook2
  @NRT_ON
  Scenario: [REWORK_DOMICILIO_DIGITALE_PG_65_66] - Visualizza banner - Notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Attesa 1 secondi
    And Si visualizza correttamente il banner di recapito di cortesia mancante
    And La persona giuridica clicca sulla prima notifica restituita
    And Si visualizza correttamente il banner di recapito di cortesia mancante