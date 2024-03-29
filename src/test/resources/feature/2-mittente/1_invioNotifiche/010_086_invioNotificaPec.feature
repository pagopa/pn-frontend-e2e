Feature: Mittente genera una notifica tramite destinatario con pec

  @TestSuite
  @TA_invioNotificaConPec
  @mittente
  @invioNotifiche

  Scenario: PN-9644 - Mittente genera una notifica tramite destinatario con pec
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisicaPec"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisicaPec"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Si verifica che la notifica sia nello stato avanzato
    And Cliccare sulla notifica restituita
    And Si verifica che l'invio della pec sia in corso
    And Logout da portale mittente