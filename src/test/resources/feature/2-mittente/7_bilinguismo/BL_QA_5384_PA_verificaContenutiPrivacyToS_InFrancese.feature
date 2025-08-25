Feature: PA Verifica contenuti Privacy e ToS in Francese

  @TestSuite_BROWSER
  @TA_bilinguismoVerificaContenutiPrivacyToSInFrancese_QA5384
  @TA_Francese
  @bilinguismo

  Scenario: PN-QA5384-BL - PA - Verifica contenuti Privacy e ToS in Francese

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    And Si clicca bottone accetta cookies
    When Login con mittente Comune di "Viggiu"
    And Si clicca bottone accetta cookies
    And Click entra su Send Mittente
    And Home page mittente viene visualizzata correttamente

    And Verifica footer lingua "Français"
    And Verifica click footer privacy o Termini Condizione "Charte de confidentialité"
    And Verifica traduzione testo "Déclaration sur le traitement des données à caractère personnel"
    And Verifica traduzione testo "Responsable du traitement"
    And Verifica traduzione testo "Délégué Protection Données"
    And Verifica traduzione testo "Catégories de données et finalités"
    And Verifica click footer privacy o Termini Condizione "Conditions générales"
    And Verifica traduzione testo "Conditions générales d’utilisation"
    And Verifica traduzione testo "Description du Service"
    And Verifica traduzione testo "Adhésion à la Plateforme par l’AP Émettrice et résiliation"
    And Verifica traduzione testo "Identification et accès à la Plateforme par un Utilisateur"

    And Cambia lingua footer "Anglais"
    And Verifica click footer privacy o Termini Condizione "Privacy Policy"
    And Verifica traduzione testo "Privacy Notice"
    And Verifica traduzione testo "Data Controller"
    And Verifica traduzione testo "Data Protection Officer"
    And Verifica traduzione testo "Data categories and purposes"
    And Verifica click footer privacy o Termini Condizione "Terms and Conditions"
    And Verifica traduzione testo "Terms and conditions of use"
    And Verifica traduzione testo "Description of the service"
    And Verifica traduzione testo "Registering with the Platform by the sending PA and withdrawal"
    And Verifica traduzione testo "Identification and login to the Platform by a User"

    And Cambia lingua footer "Slovenian"
    And Verifica click footer privacy o Termini Condizione "Obvestilo o varovanju zasebnosti"
    And Verifica traduzione testo "Informacije o obdelavi osebnih podatkov"
    And Verifica traduzione testo "Upravljavec podatkov"
    And Verifica traduzione testo "Pooblaščenec za varstvo podatkov"
    And Verifica traduzione testo "Kategorije podatkov in nameni obdelave"
    And Verifica click footer privacy o Termini Condizione "Pogoji in določila"
    And Verifica traduzione testo "Pogoji in določila uporabe"
    And Verifica traduzione testo "Opis storitve"
    And Verifica traduzione testo "Članstvo v platformi s strani pošiljatelja JU in odstop"
    And Verifica traduzione testo "Identifikacija in dostop uporabnika do platforme"

    And Cambia lingua footer "Nemško"
    And Verifica click footer privacy o Termini Condizione "Datenschutzerklärung"
    And Verifica traduzione testo "Datenschutzerklärung"
    And Verifica traduzione testo "Verantwortlicher der Verarbeitung"
    And Verifica traduzione testo "Datenschutzbeauftragter"
    And Verifica traduzione testo "Datenkategorien und Zwecke"
    And Verifica click footer privacy o Termini Condizione "Allgemeine Geschäftsbedingungen"
    And Verifica traduzione testo "Nutzungsbedingungen"
    And Verifica traduzione testo "Beschreibung des Dienstes"
    And Verifica traduzione testo "Beitritt zur Plattform durch die ausgebende ÖV und Rücktritt"
    And Verifica traduzione testo "Identifizierung und Zugriff auf die Plattform durch einen Benutzer"

    And Cambia lingua footer "Italienisch"
    And Verifica click footer privacy o Termini Condizione "Informativa Privacy"
    And Verifica traduzione testo "Informativa sul trattamento dei dati personali"
    And Verifica traduzione testo "Titolare del trattamento"
    And Verifica traduzione testo "Responsabile Protezione"
    And Verifica traduzione testo "Categorie di dati e finalit"
    And Verifica traduzione testo "Base giuridica del trattamento"
    And Verifica traduzione testo "Categorie di destinatari"
    And Verifica traduzione testo "Trasferimenti verso paesi terzi"
    And Verifica traduzione testo "Periodo di conservazione"
    And Verifica click footer privacy o Termini Condizione "Termini e Condizioni"
    And Verifica traduzione testo "Termini e condizioni"
    And Verifica traduzione testo "Descrizione del Servizio"
    And Verifica traduzione testo "Adesione alla Piattaforma da parte della PA Mittente e recesso"
    And Verifica traduzione testo "Identificazione e accesso alla Piattaforma da parte di un Utente"
    And Verifica traduzione testo "Verifica della qualifica di legale rappresentante della PA Mittente e ruoli degli Utenti"

    Then Cambia lingua footer "Francese"
    And Verifica click footer privacy o Termini Condizione "Charte de confidentialité"
    And Verifica traduzione testo "Déclaration sur le traitement des données à caractère personnel"
    And Verifica traduzione testo "Responsable du traitement"
    And Verifica traduzione testo "Délégué Protection Données"
    And Verifica traduzione testo "Catégories de données et finalités"
    And Verifica click footer privacy o Termini Condizione "Conditions générales"
    And Verifica traduzione testo "Conditions générales d’utilisation"
    And Verifica traduzione testo "Description du Service"
    And Verifica traduzione testo "Adhésion à la Plateforme par l’AP Émettrice et résiliation"
    And Verifica traduzione testo "Identification et accès à la Plateforme par un Utilisateur"