Feature: persona giuridica scarica attestazioni all'interno di una notifica

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then pagina Piattaforma  Notifiche persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica cliccare sul bottone Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche  persona giuridica inserire il codice IUN da dati notifica "datiNotifica"

  Scenario: persona giuridica scarica attestazione

    When Il persona giuridica clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona giuridica
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona giuridica, e si controlla che il download sia avvenuto
    And Si clicca sul opzione Vedi Dettaglio
    And Logout da portale persona giuridica