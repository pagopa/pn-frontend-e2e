Feature: Ricerca notifica per periodo temporale persona fisica

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @test18
  @restLogin

  Scenario: La persona fisica fa una ricerca per date
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica inserire un arco temporale
    And Cliccare sul bottone Filtra persona fisica
    Then Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And Se i risultati sono contenuti in più pagine persona fisica è possibile effettuare il cambio pagina
    And Logout da portale persona fisica