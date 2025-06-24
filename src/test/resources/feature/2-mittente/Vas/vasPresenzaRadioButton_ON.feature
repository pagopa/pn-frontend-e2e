Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  @TestSuite
  @TA_VAS_29_32_33_34
  @NRT_PHYSICAL_ADDRESS_LOOKUP_ON
  @NRT_Blocco_3_prova
  Scenario: [VAS_29_32_33_34_ON] - La sezione "destinatari" relativa alla creazione della notifica, sia coerente con il figma nella parte dei radio buttons group
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "VAS_29_32_33_34_ON"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |

    # VAS_29_32_33
    And Verifica presenza radion Button Inserimento automatico abilitato di default e manuale disabilitato
    And Verifica abilitazione Tasto Continua
    And Cliccare su continua
    Then Verifica Pagina  Invia una nuova notifica la sezione Posizione Debitoria
