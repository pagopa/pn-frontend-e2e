Feature: PG -  PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5286
  @multiLingua
  # @NRT
  Scenario: PN-QA5286-ML - PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- FR

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Francese"
    And Si clicca su prodotto
    And Verifica traduzione testo "Procurations"
    And Verifica traduzione testo "Coordonnées"
    And Verifica traduzione testo "Utilisateurs"
 ##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifications"
    And Seleziona voce menu laterale "Notifications de l"
    And Verifica traduzione testo "Notifications de"
#    And Verifica traduzione testo "Lire les notifications de Convivio Spa"
##  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Notifications mandatées"
    And Verifica traduzione testo "Lire les notifications mandatées à Convivio Spa"
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Coordonnées"
    And Verifica traduzione testo "Ici, vous pouvez indiquer et modifier les coordonnées numériques auxquelles Convivio Spa"
#    Selezionare Stato della Piattaforma
    And Aspetta 1 secondi
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND, affiche l"
    And Verifica traduzione testo "Historique des dysfonctionnements"