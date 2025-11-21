Feature: PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - SL

  @TA_multiLinguaSloveno_QA5292
  @multiLingua
  @multiLinguaPg
  @NRT_Blocco_3
  Scenario: PN-QA5292-ML - PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - SL

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Sloveno"
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
    When Nella Pagina Notifiche persona giuridica si clicca su utenti "Uporabniki"
    And Verifica traduzione testo "Išči po imenu"
    And Verifica traduzione testo "kdo lahko bere obvestila"
    And Verifica traduzione testo "Vse vloge"
    And Verifica traduzione testo "Dodaj uporabnika"

    When Sulla Pagina Gruppi si seleziona voce menu laterale "Skupine"
    And Verifica traduzione testo "Tukaj lahko upravljate skupine podjetja in ustvarjate nove"
    And Chiudi pagina