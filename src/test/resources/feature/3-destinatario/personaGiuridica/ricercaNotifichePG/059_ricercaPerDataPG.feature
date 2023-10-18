Feature: La persona giuridica ricerca per periodo temporale

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  @TestSuite
  @fase2Test59

  Scenario: La persona giuridica ricerca per periodo temporale
    When Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche persona giuridica inserire un arco temporale
    And Cliccare sul bottone Filtra persona giuridica
    Then Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And  Logout da portale persona giuridica