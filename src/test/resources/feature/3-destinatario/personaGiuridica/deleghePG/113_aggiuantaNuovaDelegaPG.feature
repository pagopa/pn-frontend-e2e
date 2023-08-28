Feature: Il persona giuridica aggiunge una nuova delega

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica

  @TestSuite
  @fase2Test113
  @pg

  Scenario: Il persona giuridica aggiunge una nuova delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Si controlla che non sia presente una delega con stesso nome "nuovaDelegaPG" persona giuridica
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati "nuovaDelegaPG"
    And Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta
    And Nella sezione Aggiungi Delega persona giuridica salvare il codice verifica all'interno del file "nuovaDelegaPG"
    And Nella sezione Aggiungi Delega  persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma
    And Logout da portale persona giuridica
