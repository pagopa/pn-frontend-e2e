Feature: il mittente inserisce i dati fino alla sezione Destinatario

  @TA_inserimentoDatiFinoDestinatario
    @TestSuite
    @mittente
    @invioNotifiche

  Scenario Outline: PN-9136 - il mittente inserisce i dati fino alla sezione Destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    #And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica
      | ragioneSociale | Convivio Spa |
      | codiceFiscale  | 27957814470  |
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica
      | emailPec | pec@pec.pagopa.it |
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica
      | indirizzo     | VIA ROMA |
      | numeroCivico  | 20       |
      | localita      | MILANO   |
      | comune        | MILANO   |
      | provincia     | MI       |
      | codicepostale | 20147    |
      | stato         | ITALIA   |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatari persone giuridiche aggiuntivi per <numero destinatari>
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Logout da portale mittente
    Examples:
      | numero destinatari |
      | 2                  |