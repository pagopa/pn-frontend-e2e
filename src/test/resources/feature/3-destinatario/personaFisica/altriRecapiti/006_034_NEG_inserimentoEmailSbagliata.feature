Feature: La persona fisica inserisce una email sbagliata

  @TestSuite
  @TA_inserimentoEmailErrataPF
  @addressBook1
  @TA_REWORK_RECAPITI_OFF
  @NRT_Blocco_2
  Scenario: OFF_REWORK_DOMICILIO_DIGITALE_PF_PN-9308-B34 - La persona fisica inserisce una email sbagliata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi

    When Inserisci Email errata "prova..@gmail.com"
    And Si visualizza correttamente il messaggio di email non valida
    And Nella pagina I Tuoi Recapiti si inserisce un email maggiore di 255 caratteri
    And Si visualizza correttamente il messaggio di email non valida


