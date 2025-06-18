Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite_DOWN
  @TA_VAS_31
  @NRT_PHYSICAL_ADDRESS_LOOKUP_DOWN
  Scenario: [VAS_31_DOWN] - La sezione "destinatari" relativa alla creazione della notifica, Banner attivo e Inserimento manuale selezionato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "VAS_31_DOWN"
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
   And Verifica Banner attivo e Inserimento manuale selezionato

