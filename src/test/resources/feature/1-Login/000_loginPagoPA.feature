Feature: Login pagoPA

  @TA_loginMittente
  @TestSuite
  @loginFE
  @mittente_x
  Scenario: Login pagoPA mittente
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente
    And Logout da portale mittente

  @TA_loginpersonaFisica
  @TestSuite
  @loginFE
  @mittente_x
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
    And Logout da portale persona fisica

  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE
  @mittente_x
  Scenario: PN-9146 - Login pagoPA persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Logout da portale persona giuridica


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 1
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |

  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 2
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |

  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 3
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |

  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 4
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 5
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 6
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 7
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 8
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 9
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 10
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 11
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 12
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |


  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 13
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 14
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 15
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 16
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |



  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 17
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |




  @TA_loginpersonaFisica
  @TestSuite
  @loginFE_x
  @mittente_x
  Scenario: Login pagoPA persona fisica 18
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
