Feature: il delegato accetta la delega

  @TestSuite
  @TA_PFaccettaDelega
  @DeleghePF
  @PF

  Scenario: PN-9411 - il delegato accetta la delega
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico "personaFisica"
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva "personaFisica"
    And Logout da portale persona fisica