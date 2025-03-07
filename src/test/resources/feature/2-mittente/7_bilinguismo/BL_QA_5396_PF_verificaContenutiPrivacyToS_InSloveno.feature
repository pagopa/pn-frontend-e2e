Feature: PF Verifica contenuti Privacy e ToS in Sloveno

  @TestSuite
  @TA_bilinguismoPFVerificaContenutiPrivacyToSInSloveno_QA5396
  @TA_Sloveno
  @bilinguismo

  Scenario: PN-QA5396 - PF - Verifica contenuti Privacy e ToS in Sloveno

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard


    And Verifica footer lingua "Slovensko"
    And Verifica click footer privacy o Termini Condizione "Obvestilo o varovanju zasebnosti"
    And Verifica traduzione testo "Informacije o obdelavi osebnih podatkov"
    And Verifica traduzione testo "Upravljavec podatkov"
    And Verifica traduzione testo "Pooblaščenec za varstvo podatkov"
    And Verifica traduzione testo "Kategorije podatkov in nameni obdelave"
    And Verifica click footer privacy o Termini Condizione "Pogoji in določila"
    And Verifica traduzione testo "Pogoji in določila uporabe"
    And Verifica traduzione testo "Opis storitve"
    And Verifica traduzione testo "Identifikacija in dostop do platforme"
    And Verifica traduzione testo "Pooblastilo za dostop in imenovanje zaupanja vredne osebe za prevzem pri FSU/druge pooblaščene osebe"


    And Cambia lingua footer "Angleško"
    And Verifica click footer privacy o Termini Condizione "Privacy Policy"
    And Verifica traduzione testo "Privacy Notice"
    And Verifica traduzione testo "Data Controller"
    And Verifica traduzione testo "Data Protection Officer"
    And Verifica traduzione testo "Data categories and purposes"
    And Verifica click footer privacy o Termini Condizione "Terms and Conditions"
    And Verifica traduzione testo "Terms and conditions of use"
    And Verifica traduzione testo "Description of the service"
    And Verifica traduzione testo "Identification and login to the Platform"
    And Verifica traduzione testo "Delegation for login and appointment of person of trust for collection at an FSU/other authorized entity"

    And Cambia lingua footer "French"
    And Verifica click footer privacy o Termini Condizione "Charte de confidentialité"
    And Verifica traduzione testo "Déclaration sur le traitement des données à caractère personnel"
    And Verifica traduzione testo "Responsable du traitement"
    And Verifica traduzione testo "Délégué Protection Données"
    And Verifica traduzione testo "Catégories de données et finalités"
    And Verifica click footer privacy o Termini Condizione "Conditions générales"
    And Verifica traduzione testo "Conditions générales d’utilisation"
    And Verifica traduzione testo "Description du service"
    And Verifica traduzione testo "Identification et accès à la Plateforme"
    And Verifica traduzione testo "accès et désignation d’une personne de confiance pour le retrait auprès du FSU/autre personne autorisée"


    And Cambia lingua footer "Allemand"
    And Verifica click footer privacy o Termini Condizione "Datenschutzerklärung"
    And Verifica traduzione testo "Datenschutzerklärung"
    And Verifica traduzione testo "Verantwortlicher der Verarbeitung"
    And Verifica traduzione testo "Datenschutzbeauftragter"
    And Verifica traduzione testo "Datenkategorien und Zwecke"
    And Verifica click footer privacy o Termini Condizione "Allgemeine Geschäftsbedingungen"
    And Verifica traduzione testo "Nutzungsbedingungen"
    And Verifica traduzione testo "Beschreibung des Dienstes"
    And Verifica traduzione testo "Identifizierung und Zugriff auf die Plattform"
    And Verifica traduzione testo "Vollmacht für den Zugriff und Ernennung einer vertrauenswürdigen Person zur Abholung bei FSU/einer anderen autorisierten Person"


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
    And Verifica traduzione testo "Descrizione del servizio"
    And Verifica traduzione testo "Identificazione e accesso alla Piattaforma"
    And Verifica traduzione testo "accesso e nomina persona di fiducia per il ritiro presso FSU/altro soggetto autorizzato"
    And Verifica traduzione testo "Elezione domicilio digitale e invio digitale"

    Then Cambia lingua footer "Sloveno"
    And Verifica click footer privacy o Termini Condizione "Obvestilo o varovanju zasebnosti"
    And Verifica traduzione testo "Informacije o obdelavi osebnih podatkov"
    And Verifica traduzione testo "Upravljavec podatkov"
    And Verifica traduzione testo "Pooblaščenec za varstvo podatkov"
    And Verifica traduzione testo "Kategorije podatkov in nameni obdelave"
    And Verifica click footer privacy o Termini Condizione "Pogoji in določila"
    And Verifica traduzione testo "Pogoji in določila uporabe"
    And Verifica traduzione testo "Opis storitve"
    And Verifica traduzione testo "Identifikacija in dostop do platforme"
    And Verifica traduzione testo "Pooblastilo za dostop in imenovanje zaupanja vredne osebe za prevzem pri FSU/druge pooblaščene osebe"
