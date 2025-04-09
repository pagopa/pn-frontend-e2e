Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_30
  @NRT_PHYSICAL_ADDRESS_LOOKUP_OFF
  Scenario: [VAS_30] - La sezione "destinatari" relativa alla creazione della notifica, assenza radion Button Iserimento automatico e manuale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
#    //TODO Verificare con quale accedere alla piattaforma



    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
# VAS_30
    And Verifica assenza radion Button Iserimento automatico e manuale

#    Then Nella section Destinatario si inseriscono i dati del destinatario
#      | soggettoGiuridico       | PF               |
#      | nomeCognomeDestinatario | Gaio Giulio      |
#      | codiceFiscale           | CSRGGL44L13H501E |
##-------------------------------------------------------
#  # TODO DA eliminare appena sale la feature
#    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
#      | indirizzo | Via Roma              |
#      | civico    | 20                    |
#      | localita  | Milano                |
#      | comune    | Milano                |
#      | provincia | MI                    |
#      | cap       | 20147                 |
#      | stato     | Italia                |
##-------------------------------------------------------
#    # VAS_34
#    And Verifica abilitazione Tasto Continua
#    And Cliccare su continua
#    And Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria
