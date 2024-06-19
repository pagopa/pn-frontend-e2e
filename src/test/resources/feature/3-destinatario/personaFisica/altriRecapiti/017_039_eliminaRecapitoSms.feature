Feature: la persona fisica elimina numero di cellulare

  @TestSuite
  @PF
  @TA_eliminaSMSPF
  @recapitiPF

  Scenario: PN-9315 - La persona fisica elimina il numero di cellulare dai contatti
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Si verifica se il campo numero di cellulare è presente e se è valorizzato
    And Si clicca sul pulsante "elimina" relativo al campo numero di cellulare
    And Si annulla la conferma di eliminazione
    Then Si verifica che il campo numero di cellulare non sia stato eliminato
    And Si clicca sul pulsante "elimina" relativo al campo numero di cellulare
    And Si conferma l'eliminazione
    Then Si verifica che il campo numero di cellulare sia stato eliminato

