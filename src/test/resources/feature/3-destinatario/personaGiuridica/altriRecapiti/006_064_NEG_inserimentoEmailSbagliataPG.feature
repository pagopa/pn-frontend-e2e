Feature: la persona giuridica inserisce una email errata

  @TestSuite
  @PG
  @TA_inserimentoEmailErrataPG
  @recapitiPG

  Scenario: PN-9155-B63 - La persona giuridica inserisce una email errata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "prova..@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Si controlla che il tasto avvisami via email sia bloccato
    And Logout da portale persona giuridica