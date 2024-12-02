Feature: la persona giuridica inserisce un numero di telefono errato

  @TestSuite
  @PG
  @TA_inserimentoCellulareErratoPG
  @recapitiPG

  Scenario: PN-9158-B66 - La persona giuridica inserisce un numero di telefono errato
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato "2318773225"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato
    And Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato
    And Logout da portale persona giuridica