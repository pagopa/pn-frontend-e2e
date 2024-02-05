Feature: invio notifica con lo stesso codice fiscale

  @TestSuite
  @TA_invioNotificaConStessoCF
  @mittente
  @invioNotifiche

  Scenario: PN-9643 - il mittente invia la notifica con lo stesso codice fiscale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario si inserisce lo stesso destinatario di prima "personaFisica"
    Then Si visualizza correttamente l errore di stesso codice fiscale
    And Logout da portale mittente