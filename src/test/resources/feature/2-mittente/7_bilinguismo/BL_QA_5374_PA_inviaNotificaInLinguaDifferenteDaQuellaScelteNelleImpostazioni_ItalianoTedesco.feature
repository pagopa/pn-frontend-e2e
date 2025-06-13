Feature: PA invia notifica in lingua differente da quella scelte nelle impostazioni - Italiano Tedesco

  @TestSuite
  @TA_bilinguismoLinguaDifferenteDalleImpostazioni_ItalianoTedesco_QA5374
  @bilinguismo
  @NRT_BL

  Scenario: PN-QA5374-BL - PA invia notifica in lingua differente da quella scelte nelle impostazioni - Italiano Tedesco
#    Pre Condizione Aver settato nelle impostazioni Italiano
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente Comune di "Viggiu"
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente
    And Selezionare da impostazione lingua "Italiano"

    And Logout e Login con Comune di "Viggiu"
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente


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
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche













