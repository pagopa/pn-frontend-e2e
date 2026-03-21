@createNotification
Feature: Creazione nuova notifica

  Scenario: [CREATE_NOTIFICATION] Crea e invia una notifica semplice
    Given l'utente è un "admin" di "Comune di Verona"
    And una notifica di tipo "simple_notification"
    When naviga alla pagina NotificationCreate
    And compila il form con i dati della notifica
    And compila i dati del destinatario
    And seleziona la posizione debitoria
    And carica il documento allegato
    Then la notifica è stata inviata con successo
    