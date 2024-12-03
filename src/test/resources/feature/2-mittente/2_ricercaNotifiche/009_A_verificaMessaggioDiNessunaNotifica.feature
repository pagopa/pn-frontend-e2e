Feature: Mittente effetua una ricerca notifiche con diversi filtri

  @TA_MittenteRicercaNotificaSenzaResultato
  @mittente
  @ricercaNotificheMittente
  @TestSuite

  Scenario: PN-9326 - Verifica messaggio di nessuna notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale sbagliato "TYRMLK90T20Z253O"
    And Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    Then Si verifica che non ci sono notifiche disponibili
    And Logout da portale mittente