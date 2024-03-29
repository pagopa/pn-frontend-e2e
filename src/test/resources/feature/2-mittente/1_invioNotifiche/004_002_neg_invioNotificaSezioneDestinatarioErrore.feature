Feature: il mittente inserisce i dati  sbagliati fino alla sezione Destinatario

  @TA_inserimentoDatiErratiDestinatario
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-9314 - il mittente inserisce i dati sbagliati fino alla sezione Destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato
    And Nella section Destinatario inserire i dati errati dalla persona giuridica "personaGiuridicaErrore"
    And Nella section cliccare sul tasto torna a informazioni preliminari
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Logout da portale mittente