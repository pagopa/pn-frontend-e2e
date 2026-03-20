@createNotification
Feature: Creazione di una notifica dal portale SEND Mittenti
  In qualità di un utente di una PA
  voglio creare una nuova Notifica

  Scenario: [CREATE_NOTICE] Crea una nuova notifica
    Given l'utente è un "admin" di "Comune di Verona"
    When naviga alla pagina NotificationCreate
    Then la pagina deve caricarsi correttamente
    And compila il form con i dati della notifica
   # Then naviga alla pagina NotificationCreate
    