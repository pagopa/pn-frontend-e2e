Feature: PA invia notifica manuale bilingue: Italiano e Francese

  @TestSuite
  @TA_bilinguismoRefreshPaginaItalianoFrancese_QA5376
  @bilinguismo

  Scenario: PN-QA5376-BL - PA invia notifica manuale bilingue: Italiano e Francese


    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente Comune di "Viggiu"
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente

#    And Selezionare da impostazione lingua "Italiano"
#    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Selezionare da impostazione lingua "Francese"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Francese"
    And Cliccare su continua

    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
         #      TODO verificare VAS
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    Then Refresh pagina
    And verifica lingua selezionata "Italiano"
    And verifica campi vuoti



