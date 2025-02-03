Feature: PG - Verifica corretta traduzione nella pagina di Area Riservata - DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5267
  @bilinguismo

  Scenario: PN-QA5267 - PG - Verifica corretta traduzione nella pagina di Area Riservata - DE

    Given Login Page persona giuridica viene visualizzata
    And Cambia lingua footer "Tedesco"
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
      | lingua         | DE        |
#    verificare che la sezione Panoramica sia scritta in lingua Tedesca
    And Verifica traduzione testo "Übersicht"
    And Verifica traduzione testo "Zeige die Datenübersicht an und lies die Zustellungen von"
    And Verifica traduzione testo "Digitale Zustellungen"

























#                                                      #    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard Comune di "Viggiu"
#    Given Login Page mittente viene visualizzata
#      | url | https://selfcare.test.notifichedigitali.it |
#    When Login con mittente Comune di "Viggiu"
#    And Si clicca sul bottone test
#    And Si clicca bottone accetta cookies
#    And Home page mittente viene visualizzata correttamente
#
#    And Selezionare da impostazione lingua "Italiano"
##    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
#    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    And verifica lingua selezionata "Italiano"
#    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiana"
#    And Cliccare su continua
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
#    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
#    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
#    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
#    And Cliccare su continua
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
#    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
#    And Nella section Allegati cliccare sul bottone Invia
#    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
#    And Cliccare sul bottone vai alle notifiche
#    And Si visualizza correttamente la pagina Piattaforma Notifiche
##    And Si verifica che la notifica viene creata correttamente "datiNotifica"
#    And Logout e Login con Comune di "Viggiu"
#    And Si clicca sul bottone test
#    And Si clicca bottone accetta cookies
#    And Home page mittente viene visualizzata correttamente
#    And selezione impostazione lingua
#    And verifica lingua selezionata "Italiano"









