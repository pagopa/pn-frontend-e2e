Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_31
  @NRT_PHYSICAL_ADDRESS_LOOKUP_DOWN
  Scenario: [VAS_31_DOWN] - La sezione "destinatari" relativa alla creazione della notifica, Banner attivo e Inserimento manuale selezionato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
#    //TODO Verificare con quale accedere alla piattaforma



    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
   And Verifica Banner attivo e Inserimento manuale selezionato

# VAS_33
#   TODO And Verifica radion Button Inserimento manuale selezionato e Inserimento Automatico non cliccabile
#   TODO And Verifica Banner


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
