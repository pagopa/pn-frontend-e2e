Feature: il mittente inserisce i dati fino alla sezione Destinatario

    @TA_inserimentoDatiFinoDestinatario
    @TestSuite
    @mittente
      @invioNotifiche_07
      @loginFE
    Scenario Outline: PN-9136 - il mittente inserisce i dati fino alla sezione Destinatario
      Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
      When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
      And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
      And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
      And Cliccare su continua
      And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
      And Nella section Destinatario selezionare il radio button persona giuridica
      And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica
      And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica
      And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica" destinatario 0
      And Nella section Destinatario cliccare su Aggiungi destinatario
      And Nella section Destinatario inserire i dati del destinatari persone giuridiche aggiuntivi per <numero destinatari>
    #And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica" destinatario 1
      And Cliccare su continua
#      Aggiungere alle notifiche
      And Seleziona Nessun Pagamento
      And Cliccare su continua
#      Aggiungere alle notifiche

    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
#    And Logout da portale mittente
    Examples:
      | numero destinatari |
      | 2                  |