Feature: Ricerca notifica per periodo temporale Destinatario

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test18

  Scenario Outline: il destinatario fa una ricerca per date
    When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
    And Nella pagina Piattaforma Notifiche Destinatario inserire una data da <DA> a <A>
    And Cliccare sul bottone Filtra Destinatario
    Then Nella pagina Piattaforma Notifiche Destinatario vengo restituite tutte le notifiche con la data della notifica compresa con le date precedentemente inserite
    And Se i risultati sono contenuti in più pagine destinatario è possibile effettuare il cambio pagina
    And Logout da portale destinatario

    Examples:
      |     DA      |     A       |
      |  10/05/2023 | 16/05/2023  |
