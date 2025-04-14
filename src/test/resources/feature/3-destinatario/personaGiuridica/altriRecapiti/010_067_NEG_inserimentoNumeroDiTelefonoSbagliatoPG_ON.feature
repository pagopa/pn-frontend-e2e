Feature: la persona giuridica inserisce un numero di telefono errato

  @TestSuite
  @TA_inserimentoCellulareErratoPG_ON
  @addressBook2
  @NRT_ON
  Scenario: ON_REWORK_DOMICILIO_DIGITALE_PG_PN-9158-B66 - La persona giuridica inserisce un numero di telefono errato

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    # Creazione Cellulare
    When Click Inizia
    And Click Attiva
    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "2318773225" e si clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato

And Click Non ora










