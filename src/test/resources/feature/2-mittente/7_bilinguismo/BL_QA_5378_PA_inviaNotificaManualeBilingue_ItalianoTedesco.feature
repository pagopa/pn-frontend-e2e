Feature: PA invia notifica manuale bilingue: Italiano e Tedesco

  @TA_bilinguismoRefreshPaginaItalianoTedesco_QA5378
  @bilinguismo
  @NRT_Blocco_1

  Scenario: PN-QA5378-BL - PA invia notifica manuale bilingue: Italiano e Tedesco

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente Comune di "Viggiu"
    And Click entra su Send Mittente
    And Clicca tasto Accedi OneTrust PA
#    And Home page mittente viene visualizzata correttamente
    And Selezionare da impostazione lingua "Tedesco"

#    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And verifica lingua selezionata "Tedesco"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Tedesco"
    And Cliccare su continua

    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica



