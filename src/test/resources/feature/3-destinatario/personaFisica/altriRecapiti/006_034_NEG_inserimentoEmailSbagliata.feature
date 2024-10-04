Feature: La persona fisica inserisce una email sbagliata

  @TestSuite
  @TA_inserimentoEmailErrataPF
  @PF
  @recapitiPF

  Scenario: PN-9308-B34 - La persona fisica inserisce una email sbagliata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "provà&@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Si controlla che il tasto avvisami via email sia bloccato
    And Logout da portale persona fisica
