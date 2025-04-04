Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_VisualizzaBannerNotifica_PG
  @addressBook2
  @NRT_ON
  Scenario: [REWORK_DOMICILIO_DIGITALE_PG_65_66] - Visualizza banner - Notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica Attivazione Domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Aspetta 5 secondi
    And Si visualizza correttamente il banner di recapito di cortesia mancante
    And La persona giuridica clicca sulla prima notifica restituita
    And Si visualizza correttamente il banner di recapito di cortesia mancante