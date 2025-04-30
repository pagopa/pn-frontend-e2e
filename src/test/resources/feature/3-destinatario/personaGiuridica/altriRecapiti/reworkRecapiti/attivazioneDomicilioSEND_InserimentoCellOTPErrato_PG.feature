Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_inserimentoCellOTPErrato_PG
  @addressBook2
  @TA_ON
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_71] La persona giuridica loggata inserisce un OTP sbagliato cellulare
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
    When Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla