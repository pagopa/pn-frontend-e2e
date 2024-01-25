Feature: il mittente fa una ricerca combinata tra cf e data

  @TestSuite
  @mittente
  @ricercaNatoficheMittente
  @TA_MittenteRicercaPerCFeData

  Scenario: PN-9222 - il mittente fa una ricera sia per cf che per data
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Nella pagina Piattaforma Notifiche inserire una data
    And Cliccare sul bottone Filtra
    And Il sistema restituisce notifiche con codice fiscale e data uguale a quelli inserito
    And Logout da portale mittente