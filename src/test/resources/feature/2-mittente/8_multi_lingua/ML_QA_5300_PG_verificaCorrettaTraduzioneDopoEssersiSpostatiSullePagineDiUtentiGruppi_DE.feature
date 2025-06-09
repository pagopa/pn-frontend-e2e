Feature: PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5300
  @multiLingua
  @NRT
  Scenario: PN-QA5300-ML - PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - DE

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Tedesco"
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto

    When Nella Pagina Notifiche persona giuridica si clicca su utenti "Benutzer"
    And Verifica traduzione testo "Benutzer hinzufügen"
    And Verifica traduzione testo "Suchen nach Namen"
    And Verifica traduzione testo "Verwalte die Benutzer, die die Zustellungen von Convivio Spa lesen können."

    When Seleziona voce menu laterale "Gruppen"
    And Verifica traduzione testo "Hier kannst du Unternehmensgruppen verwalten und neue erstellen"
