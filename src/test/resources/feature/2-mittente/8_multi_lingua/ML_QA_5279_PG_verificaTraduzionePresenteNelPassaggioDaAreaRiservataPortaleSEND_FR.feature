Feature: PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - FR

  @TA_multiLinguaFrancese_QA5279
  @multiLingua
  @multiLinguaPg
  @NRT_Blocco_3
  Scenario: PN-QA5279-ML - PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - FR

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Francese"
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
    When Seleziona voce menu laterale "Notifications"
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
    And Verifica traduzione testo "les coordonnées numériques sur lesquelles recevoir les communications à valeur légale de SEND"
#    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND, affiche l"
    And Verifica traduzione testo "Historique des dysfonctionnements"
    And Chiudi pagina




