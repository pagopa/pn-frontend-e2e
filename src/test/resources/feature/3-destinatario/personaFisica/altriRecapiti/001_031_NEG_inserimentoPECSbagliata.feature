Feature: La persona fisica inserisce una PEC sbagliata

  @TestSuite
  @TA_inserimentoPECErrataPF
  @recapitiPF
  @PF
  @Alima

  Scenario: PN-9240-B31 - La persona fisica inserisce una PEC sbagliata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC errata "testpagopa2[@fail.it"
    Then Si visualizza correttamente il messaggio di pec errata
    And Si controlla che il tasto conferma sia bloccato
    And Logout da portale persona fisica