Feature: il mittente fa una ricerca combinata tra cf e periodo temporale

  @TestSuite
  @TA_MittenteRicercaPerCFePertiodo
  @mittente
  @ricercaNotificheMittente

  Scenario: PN-9222 - il mittente fa una ricera sia per cf che per periodo temporale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "CSRGGL44L13H501E"
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito
    And Logout da portale mittente