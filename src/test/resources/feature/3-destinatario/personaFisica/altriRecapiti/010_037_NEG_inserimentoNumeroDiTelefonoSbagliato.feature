Feature: la persona fisica inserisce un numero di telefono errato

  @TestSuite
  @TA_inserimentoTelefonoErratoPF
  @PF
  @recapitiPF
   @Alima

  Scenario: PN-9311-B37 - La persona fisica inserisce un numero di telefono errato
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato "2318773225"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato
    And Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato
    And Logout da portale persona fisica