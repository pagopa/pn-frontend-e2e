Feature: Il mittente inserisce 2 destinatari e viene eliminato il primo

  @TA_checkAggiuntaCancellazioneDestinatario
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-8902 - il mittente inserisce i dati sbagliati fino alla sezione Destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario cliccare su Rimuovi destinatario
    Then Nella section Destinatario viene visualizzato un solo destinatario
    And Logout da portale mittente