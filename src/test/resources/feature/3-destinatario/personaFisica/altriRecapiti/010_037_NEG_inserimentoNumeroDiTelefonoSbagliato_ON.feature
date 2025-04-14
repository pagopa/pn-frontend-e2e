Feature: la persona fisica inserisce un numero di telefono errato

  @TestSuite
  @TA_inserimentoTelefonoErratoPF_ON
  @addressBook1
  @RW_NRT_ON
  Scenario: ON_REWORK_DOMICILIO_DIGITALE_PF_PN-9311-B37 - La persona fisica inserisce un numero di telefono errato
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    When Click Inizia
    And Click Attiva
    When Click Bottone "Aggiungi un numero di cellulare"

    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "2318773225" e si clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato
