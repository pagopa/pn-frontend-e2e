Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_VisualizzaBannerNotifica_PG
#  @addressBook2
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_1

  Scenario: [REWORK_DOMICILIO_DIGITALE_PG_65_66] - Visualizza banner - Notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
#   Attivazione Domicilio Digitale
    When Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Click Lo Faro piu tardi
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    And Attesa 1 secondi
    When Verifica Attivazione Domicilio digitale
#   Check banner notifiche
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    #And Aspetta 5 secondi
    And Refresh pagina
    And Si visualizza correttamente il banner di recapito di cortesia mancante
    And La persona giuridica clicca sulla prima notifica restituita
    #And Aspetta 1 secondi
    And Refresh pagina
    And Si visualizza correttamente il banner di recapito di cortesia mancante