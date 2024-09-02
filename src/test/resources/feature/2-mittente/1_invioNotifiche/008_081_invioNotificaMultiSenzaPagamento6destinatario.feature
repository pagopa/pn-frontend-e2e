Feature: il mittente invia una notifica con 6 destinatario

  @TestSuite
    @TA_invioNotifica6DestinatariSenzaPagamento
    @mittente
    @invioNotifiche

  Scenario Outline: PN-9227 - il mittente invia una notifica con 6 destinatario
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
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica
      | name          | Gaio Giulio      |
      | familyName    | Cesare           |
      | codiceFiscale | CSRGGL44L13H501E |
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica
      | indirizzo     | VIA ROMA |
      | numeroCivico  | 20       |
      | localita      | MILANO   |
      | comune        | MILANO   |
      | provincia     | MI       |
      | codicepostale | 20147    |
      | stato         | ITALIA   |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati delle persone fisiche aggiuntive per <numero destinatari>
    And Nella section Destinatario si cerca di aggiungere il sesto destinatario
    And Logout da portale mittente
    Examples:
      | numero destinatari |
      | 5                  |