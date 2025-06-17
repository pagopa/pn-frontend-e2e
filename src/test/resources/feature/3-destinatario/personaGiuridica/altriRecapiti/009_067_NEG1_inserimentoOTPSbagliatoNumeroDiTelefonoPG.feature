Feature: la persona giuridica inserisce l'OTP numero di telefono  errato

  @TestSuite
  @addressBook2
  @TA_OFF
  @TA_inserimentoOTPTelefonoErratoPG
  @GestioneErrori
  Scenario: [OFF_REWORK_DOMICILIO_DIGITALE_PG_PN-9158-A66] - La persona giuridica inserisce l'OTP numero di telefono errato

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    # Creazione Cellulare
    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3328560082" e si clicca sul bottone avvisami via SMS
    And  Si clicca sul bottone del pop-up ok ho capito

    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
