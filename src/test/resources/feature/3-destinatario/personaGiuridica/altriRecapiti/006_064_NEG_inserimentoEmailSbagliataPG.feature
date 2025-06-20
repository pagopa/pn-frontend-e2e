Feature: la persona giuridica inserisce una email errata

  @TestSuite_OFF
#  @TA_inserimentoEmailErrataPG
  @addressBook2
  @TA_REWORK_RECAPITI_OFF
  @NRT_Blocco_1

  Scenario: OFF_REWORK_DOMICILIO_DIGITALE_PG_PN-9155-B63 - La persona giuridica inserisce una email errata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi

    When Inserisci Email errata "prova..@gmail.com"
    And Si visualizza correttamente il messaggio di email non valida
    And Nella pagina I Tuoi Recapiti si inserisce un email maggiore di 255 caratteri
    And Si visualizza correttamente il messaggio di email non valida


