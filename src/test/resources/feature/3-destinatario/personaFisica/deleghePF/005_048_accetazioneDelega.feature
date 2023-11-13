Feature: il delegato accetta la delega

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @TA_PFaccettaDelega
  @DeleghePF
  @PF

  Scenario: il delegato accetta la delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico "personaFisica"
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega a lo stato Attiva "personaFisica"
    And Logout da portale persona fisica