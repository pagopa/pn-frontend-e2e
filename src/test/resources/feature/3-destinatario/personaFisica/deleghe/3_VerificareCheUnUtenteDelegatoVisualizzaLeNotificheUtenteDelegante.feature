Feature: il delegato accetta la delega

  @TestSuite
  @TA_PF_3_VerificareCheUnUtenteDelegatoVisualizzaLeNotificheUtenteDelegante
  @DeleghePF
  @PF
  @deleghe1
  @DeleghePFPG
  @NRT_Blocco_3
  Scenario: [QA-8819_3] Verificare Che Un Utente Delegato Visualizza Le Notifiche Utente Delegante
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe Rifiuta Deleghe Esistenti
    When Creo in background una delega per persona fisica
      | accessoCome | delegante        |
      | fiscalCode  | BRGLRZ80D58H501Q |
      | person      | true             |
      | displayName | Lucrezia Borgia  |
      | firstName   | Lucrezia         |
      | lastName    | Borgia           |
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |
    And Click Notifiche
    And Click Notifica "Gaio Giulio Cesare"
    And Verifica Esistenza Tabella Notifiche