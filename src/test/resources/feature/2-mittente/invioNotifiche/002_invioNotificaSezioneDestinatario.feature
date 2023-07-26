Feature: il mittente inserisce i dati fino alla sezione Destinatario
  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

    @fase2Test2
      @provaRun

  Scenario Outline: il mittente inserisce i dati fino alla sezione Destinatario
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridca"
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati del destinatario "destinatario"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatari aggiuntivi  per <numero destinatari>
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Logout da portale mittente
    Examples:
      | numero destinatari |
      |         1          |