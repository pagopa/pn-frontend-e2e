Feature: PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5285
  @multiLingua
  @NRT
  Scenario: PN-QA5285- PG -  Verifica corretta traduzione dopo essersi spostati sulle pagine di Utenti e Gruppi - FR

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Francese"
  #    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto

    When Nella Pagina Notifiche persona giuridica si clicca su utenti "Utilisateurs"
    And Verifica traduzione testo "Recherche par nom"
    And Verifica traduzione testo "utilisateurs qui peuvent lire les notifications de Convivio Spa"
    And Verifica traduzione testo "Ajouter utilisateur"

    When Seleziona voce menu laterale "Groupes"
    And Verifica traduzione testo "utilisateurs, par exemple appartenant au même bureau ou département, auxquels est confiée la gestion des notifications"
