Feature: PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - FR

  @TA_multiLinguaFrancese_QA5269
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5269-ML - PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - FR
    Given Login Page persona fisica test viene visualizzata
    #When Login con persona fisica scelta lingua
    And Si clicca bottone accetta cookies
    And Cambia lingua footer "Francese"
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Clicca tasto Accedi OneTrust PG e PF
    And Attesa 2 secondi
    And Refresh pagina
    And Attesa 2 secondi
    And Verifica traduzione testo "Vos notifications"
    #   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Tes coordonnées"
    And Verifica traduzione testo "Délégations"
    And Verifica traduzione testo "Statut de la plateforme"
  #   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Objet"
    And Verifica traduzione testo "État"
#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Tes coordonnées"
    And Verifica traduzione testo "Adresses"
    And Verifica traduzione testo "Gérez ici les adresses numériques sur lesquelles recevoir les communications à valeur légale de SEND"
    And Verifica traduzione testo "domicile digitale"
    And Verifica traduzione testo "adresse e-mail"
  #  Raggiungere la sezione deleghe e verificarne la traduzione
    When Seleziona voce menu laterale "Délégations"
    And Verifica traduzione testo "Ici, vous pouvez gérer vos mandataires et les procuration à votre charge"
    And Verifica traduzione testo "Vos mandataires"
    And Verifica traduzione testo "Procurations à votre charge"
    And Verifica traduzione testo "Ajouter une procuration"
#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    Then Seleziona voce menu laterale "Statut de la plateforme"
    And Verifica traduzione testo "historique des dysfonctionnements et télécharge les attestations correspondantes opposables à des tiers"
    And Chiudi pagina