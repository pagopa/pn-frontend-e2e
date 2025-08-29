Feature:La persona fisica visualizza il codice  di una delega

  @TestSuite
  @TA_PFvisualizzaCodiceDelega
  @NRT_Blocco_3
  @deleghe1
  @DeleghePFPG
  @NRT_PN13213
  Scenario:PN-9402 - La persona fisica visualizza il codice  di una delega
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu dei delegati
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione mostra codice
    And Si visualizza correttamente la modale mostra codice
    Then Si clicca sul bottone chiudi
