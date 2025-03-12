Feature: PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5292
  @multiLingua
  @NRT_1
  Scenario: PN-QA5292 - PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - SL

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Sloveno"
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto

    When Nella Pagina Notifiche persona giuridica si clicca su utenti "Uporabniki"
    And Verifica traduzione testo "Išči po imenu"
    And Verifica traduzione testo "kdo lahko bere obvestila"
    And Verifica traduzione testo "Vse vloge"
    And Verifica traduzione testo "Dodaj uporabnika"

    When Seleziona voce menu laterale "Skupine"
    And Verifica traduzione testo "Tukaj lahko upravljate skupine podjetja in ustvarjate nove"
