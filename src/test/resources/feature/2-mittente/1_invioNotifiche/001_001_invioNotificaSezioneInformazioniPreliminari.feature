Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TA_inserimentoDatiInfoPreliminari
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-9127 - Il mittente inserisce i dati nella sezione informazioni preliminari
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
   # And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Logout da portale mittente