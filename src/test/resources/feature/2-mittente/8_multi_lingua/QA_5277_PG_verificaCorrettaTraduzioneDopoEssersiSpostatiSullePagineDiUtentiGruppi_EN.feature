Feature: PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - EN

  @TestSuite
  @TA_multiLinguaInglese_QA5277
  @multiLingua

  Scenario: PN-QA5277 - PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - EN

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Inglese"
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"

    When Nella Pagina Notifiche persona giuridica si clicca su utenti "Users"
    And Verifica traduzione testo "Search by name"
    And Verifica traduzione testo "Name"
    And Verifica traduzione testo "Role"

    When Seleziona voce menu laterale "Groups"
    And Verifica traduzione testo "Groups are a set of users, for example, belonging to the same office or department, who are entrusted with notification management"
