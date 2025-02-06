Feature: PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5269
  @bilinguismo
  Scenario: PN-QA5269 - PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - FR
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Francese |
    And Refresh pagina
    And Verifica traduzione testo "Vos notifications"
    And Verifica traduzione testo "Vos adresses"
    And Verifica traduzione testo "Procurations"
    #   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Vos adresses"
    And Verifica traduzione testo "Procurations"
    And Verifica traduzione testo "État de la plateforme"
  #   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Objet"
    And Verifica traduzione testo "État"
#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Vos adresses"
    And Verifica traduzione testo "Adresses"
    And Verifica traduzione testo "Vous pouvez ici gérer les adresses auxquelles recevoir les notifications des organismes ayant adhéré à SEND"
    And Verifica traduzione testo "Adresse à valeur légale"
    And Verifica traduzione testo "Adresse PEC"
    And Verifica traduzione testo "Adresse email"
  #  Raggiungere la sezione deleghe e verificarne la traduzione
    When Seleziona voce menu laterale "Procurations"
    And Verifica traduzione testo "Ici, vous pouvez gérer vos mandataires et les procuration à votre charge"
    And Verifica traduzione testo "Vos mandataires"
    And Verifica traduzione testo "Procurations à votre charge"
    And Verifica traduzione testo "Ajouter une procuration"
#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "historique des dysfonctionnements et télécharge les attestations correspondantes opposables à des tiers"