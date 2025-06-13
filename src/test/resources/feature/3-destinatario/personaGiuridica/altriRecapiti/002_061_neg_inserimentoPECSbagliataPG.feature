Feature: La persona giuridica inserisce una PEC

  @TestSuite_OFF
  @TA_inserimentoPECErrataPG
  @addressBook2
  @TA_REWORK_RECAPITI_OFF
  @NRT

  Scenario: OFF_REWORK_DOMICILIO_DIGITALE_PG_PN-9152-B60 - La persona giuridica loggato inserisce una PEC errata

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    When Click Inizia
    And Inserisci Pec Errata "testpagopa2@@pnpagopa.postecert.local"
    And Si visualizza correttamente il messaggio di pec non valida
    And Nella pagina I Tuoi Recapiti si inserisce un PEC maggiore di 255 caratteri
    And Si visualizza correttamente il messaggio di pec non valida
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale

