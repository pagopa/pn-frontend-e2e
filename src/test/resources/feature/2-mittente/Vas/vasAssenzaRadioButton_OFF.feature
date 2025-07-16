Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite_OFF
  @TA_VAS_30
  @NRT_PHYSICAL_ADDRESS_LOOKUP_OFF


  Scenario: [VAS_30_OFF] - La sezione "destinatari" relativa alla creazione della notifica, assenza radion Button Inserimento automatico e manuale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "VAS_30_OFF"
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
# VAS_30
    And Verifica assenza radion Button Inserimento automatico e manuale

    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |
