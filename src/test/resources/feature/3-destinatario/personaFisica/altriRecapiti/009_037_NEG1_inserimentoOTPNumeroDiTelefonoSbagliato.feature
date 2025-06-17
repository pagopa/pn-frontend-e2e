Feature: la persona fisica inserisce l'OTP numero di telefono  errato

  #  @TestSuite
  @TA_inserimentoOTPTelefonoErratoPF
  @TA_OFF
  @addressBook1
  @GestioneErrori
  Scenario: [PN-9311-C37-PN_USERATTRIBUTES_EXPIREDVERIFICATIONCODE] - la persona fisica inserisce l'OTP numero di telefono errato
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare

    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3328560082" e si clicca sul bottone avvisami via SMS
    And  Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla