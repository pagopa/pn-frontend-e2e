Feature: il mittente inserisce tutti i dati di una notifica senza allegati

  @TA_invioNotificaSenzaAllegati
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-9642 - il mittente inserisce tutti i dati di una notifica senza allegati
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica"
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto con estenzione non valida
    Then Si visualizza correttamente il messaggio di errore estensione file non supportata. Riprovare con un altro file.
    And Logout da portale mittente
