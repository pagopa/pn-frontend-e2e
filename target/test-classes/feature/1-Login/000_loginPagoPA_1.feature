Feature: Login pagoPA Demo1

  @demo
  Scenario: Login pagoPA mittente
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA persona fisica
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    Then Home page persona fisica viene visualizzata correttamente

  @demo
  Scenario: PN-9146 - Login pagoPA persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA mittente 1
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA persona fisica 1
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    Then Home page persona fisica viene visualizzata correttamente

  @demo
  Scenario: PN-9146 - Login pagoPA persona giuridica 1
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA mittente 2
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA persona fisica 2
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    Then Home page persona fisica viene visualizzata correttamente

  @demo
  Scenario: PN-9146 - Login pagoPA persona giuridica 2
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente


  @demo
  Scenario: Login pagoPA mittente 3
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente

  @demo
  Scenario: Login pagoPA persona fisica 3
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    Then Home page persona fisica viene visualizzata correttamente

  @demo
  Scenario: PN-9146 - Login pagoPA persona giuridica 3
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente



