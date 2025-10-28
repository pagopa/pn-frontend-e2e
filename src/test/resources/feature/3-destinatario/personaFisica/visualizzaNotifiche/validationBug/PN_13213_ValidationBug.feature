Feature: La persona giuridica aggiunge una nuova delega

  @TA_PN_13213_PF
  @NRT_Blocco_3
  Scenario: [PN_13213_PF] ValidationBug [FE - a11y] Label per le radio button e checkbox
  #PN-16180
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test    |
      | comune | Verona |
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Nella pagina Piattaforma Notifiche mittente si accede alla notifica con codice IUN specifico
      | dev  | KZTL-KLPK-DRZU-202508-N-1 |
      | test | PUGW-RUDZ-VQZR-202508-V-1 |
      | uat  | RPRV-ZVQH-EHGU-202510-G-1 |
    And Si controlla che non ci sia il campo "Importo" nel dettaglio notifica
    And Si controlla che non ci sia il campo "Da pagare entro il" nel dettaglio notifica

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Click entra su Send Persona Giuridica
    And Nella pagina Piattaforma Notifiche persona giuridica si accede alla notifica con codice IUN specifico
     | dev  | KZJA-GWZK-RVMK-202508-H-1 |
     | test | PAYK-TYXN-HUNJ-202510-J-1 |
     | uat  | ZTZT-QPMU-WZWT-202510-T-1 |
    And Si controlla che non ci sia il campo "Da pagare entro il" nel dettaglio notifica
#PN_13213
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN specifico
      | dev  | KZTL-KLPK-DRZU-202508-N-1 |
      | test | PUGW-RUDZ-VQZR-202508-V-1 |
      | uat  | RPRV-ZVQH-EHGU-202510-G-1 |
    #And Si controlla se la sezione pagamento visualizzata correttamente
    And Si controlla che non ci sia il campo "Da pagare entro il" nel dettaglio notifica
    And Si clicca sui radio button del pagamento
    And Cliccare sul bottone Paga