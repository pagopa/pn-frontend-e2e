Feature: Rework Page 404

  @TA_Rework_Page_404
  @TA_404_mittente
  @NRT_Blocco_1
  Scenario: Rework Page 404 mittente
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nel portale Send "pubblica amministrazione" accedere ad una rotta non esistente
    And Verifica esistenza Pagina non trovata
    And Click Torna alla home


  @TA_Rework_Page_404
  @TA_404_persona_fisica
  @NRT_Blocco_1
  Scenario: Rework Page 404 persona fisica
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nel portale Send "persona fisica" accedere ad una rotta non esistente
    And Verifica esistenza Pagina non trovata
    And Click Torna alla home

  @TA_Rework_Page_404
  @TA_404_persona_giuridica
  @NRT_Blocco_1
  Scenario: Rework Page 404 persona giuridica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nel portale Send "persona giuridica" accedere ad una rotta non esistente
    And Verifica esistenza Pagina non trovata
    And Click Torna alla home


