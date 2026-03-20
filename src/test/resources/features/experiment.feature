@experiment
Feature: Navigazione portale SEND Mittenti
  In qualità di un utente di una PA
  voglio navigare il portare SEND Mittenti
  così posso verificare che ogni pagina sia raggiungibile


  Scenario: [READ_NOTICE] Verifica la raggiungibilità del dettaglio della notifica
    Given l'utente è un "admin" di "Comune di Verona"
    When seleziona la prima notifica dalla Dashboard 
    Then la pagina della notifica deve caricarsi correttamente