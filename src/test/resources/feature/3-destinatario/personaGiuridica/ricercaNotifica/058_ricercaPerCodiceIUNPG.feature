Feature: La persona giuridica ricerca per codice IUN

  @TestSuite
  @TA_PGricercaNotificaPerIUN
  @RicercaNotifichePG
  @PG

  Scenario: PN-9149 - La persona giuridica ricerca per codice IUN
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "datiNotificaPG"
    And Cliccare sul bottone Filtra persona giuridica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
    And  Cliccare sul bottone Rimuovi filtri persona giuridica
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN non valido da dati notifica "5252-5252-5252"
    And Cliccare sul bottone Filtra persona giuridica
    Then Viene visualizzato un messaggio in rosso di errore sotto il campo errato e il rettangolo diventa rosso e il tasto Filtra è disattivo
    And  Logout da portale persona giuridica

