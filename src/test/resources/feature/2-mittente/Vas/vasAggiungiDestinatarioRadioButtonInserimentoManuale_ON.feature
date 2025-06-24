Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_37_38
  @NRT_PHYSICAL_ADDRESS_LOOKUP_ON
  @NRT_Blocco_3_prova
  Scenario: [VAS_37_38_ON] - Selezionando la modalità di inserimento manuale dell'indirizzo tramite il radio button <Inserimento manuale> In Aggiungi un destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "VAS_37_38_ON"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |

    # VAS_37
    #-------------------------------------------------------
    And Verifica radion button Inserimento Automatico abilitato di default
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Aggiungi Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Lovelace Ada     |
      | codiceFiscale           | LVLDAA85T50G702B |
    And Seleziona radion button Inserimento Manuale se esiste "1"
    And Nella section Aggiungi Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |

#-------------------------------------------------------
   # VAS_38
    Then Verifica Disibilitato Tasto Continua
    And Nella section Aggiungi Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | civico | 20 |
    And Cliccare su continua
    And Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria



