Feature: Il delegato visualizza la notifiche del delegante

  Background: Login delegato
    Given Login Page delegato "delegato" viene visualizzata
    When Login con delegato "delegato"
    Then Home page delegato viene visualizzata correttamente

  @test50

  Scenario:
    When Nella home page delegato si clicca sul bottone Notifiche
    And Si visualizza correttamente la home page delegato
    And Si visualizza l elenco delle notifiche relative al delegante
    And Si seleziona il nome del delegante nell elenco
    Then Si visualizzano tutte le notifiche del delegante selezionato
    And  Logout da portale delegato