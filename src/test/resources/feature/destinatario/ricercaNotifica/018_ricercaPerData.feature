Feature: Ricerca notifica per periodo temporale Destinatario

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login portale destinatario tramite request method
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test18
  @restLogin

  Scenario: il destinatario fa una ricerca per date
    When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
    And Nella pagina Piattaforma Notifiche Destinatario inserire un arco temporale
    And Cliccare sul bottone Filtra Destinatario
    Then Nella pagina Piattaforma Notifiche Destinatario vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And Se i risultati sono contenuti in più pagine destinatario è possibile effettuare il cambio pagina
    And Logout da portale destinatario