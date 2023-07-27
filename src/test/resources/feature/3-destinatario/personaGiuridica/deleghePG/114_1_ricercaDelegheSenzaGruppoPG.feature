Feature: Il persona giuridica visuallizza le deleghe

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente


  Scenario: Il persona giuridica visuallizza le deleghe
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si insrisce il codice fiscale del delegante "personaFisica"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Flitra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito "persnoFisica"
    And Logout da portale persona giuridica