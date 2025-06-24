Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_36_35
  @NRT_PHYSICAL_ADDRESS_LOOKUP_ON
  @NRT_Blocco_3_prova
  Scenario: [VAS_36_35_ON] - Selezionando la modalità di inserimento manuale dell'indirizzo tramite il radio button <Inserimento manuale>
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "VAS_36_35_ON"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |

    # VAS_36
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma              |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |

    And Verifica Disibilitato Tasto Continua
   # VAS_35
    Then Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | civico    | 20                    |
    And Cliccare su continua
    And Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria