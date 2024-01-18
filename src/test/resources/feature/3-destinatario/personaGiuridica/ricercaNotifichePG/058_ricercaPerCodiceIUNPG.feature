Feature: La persona giuridica ricerca per codice IUN

  Background: Login persona giuridica
     Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"
  @TestSuite
  @TA_PGricercaNotificaPerIUN
  @RicercaNotifichePG
  @PG

  Scenario: PN-9149 - La persona giuridica ricerca per codice IUN
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "datiNotificaPG"
    And Cliccare sul bottone Filtra persona giuridica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotificaPG"
    And  Cliccare sul bottone Rimuovi filtri persona giuridica
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN non valido da dati notifica "datiNotificaNonValidoPG"
    And Cliccare sul bottone Filtra persona giuridica
    Then Viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo
    And  Logout da portale persona giuridica

