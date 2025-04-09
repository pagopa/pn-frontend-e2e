Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_37_38
  @NRT_PHYSICAL_ADDRESS_LOOKUP_ON
  Scenario: [VAS_37_38] - Selezionando la modalità di inserimento manuale dell'indirizzo tramite il radio button <Inserimento manuale> In Aggiungi un destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
#    //TODO Verificare con quale accedere alla piattaforma



    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario


    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |

    # VAS_37
    #-------------------------------------------------------
 And Verifica radion button Inserimento Automatico abilitato di default

## da eliminare
#    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
#      | indirizzo | Via Roma              |
#      | localita  | Milano                |
#      | comune    | Milano                |
#      | provincia | MI                    |
#      | cap       | 20147                 |
#      | stato     | Italia                |
#      | civico    | 20                    |
    And Nella section Destinatario cliccare su Aggiungi destinatario


    
    Then Nella section Aggiungi Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Lovelace Ada      |
      | codiceFiscale           | LVLDAA85T50G702B |

    And Seleziona radion button Inserimento Manuale se esiste

    And Nella section Aggiungi Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma              |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |

#-------------------------------------------------------
   # VAS_38
    And Verifica Disibilitato Tasto Continua
    And Nella section Aggiungi Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | civico    | 20                    |
    And Cliccare su continua
    And Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria



