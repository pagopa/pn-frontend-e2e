Feature: il mittente fa una ricerca combinata tra cf e periodo temporale

  @TA_MittenteRicercaPerCFePertiodo
  @mittente
  @ricercaNotificheMittente
  @NRT_Blocco_1
  Scenario: PN-9222_1 - il mittente fa una ricera sia per cf che per periodo temporale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e arco temporale uguale a quelli inserito