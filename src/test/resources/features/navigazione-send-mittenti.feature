Feature: Navigazione portale SEND Mittenti
  In qualità di un utente di una PA
  voglio navigare il portare SEND Mittenti
  così posso verificare che ogni pagina sia raggiungibile

  Scenario Outline: [] Verifica la raggiungibilità delle pagine
    Given l'utente è un "admin" di "Comune di Milano"
    When naviga alla pagina "<Pagina>" tramite la route "<Route>"
    Then la pagina deve caricarsi correttamente

    Examples:
      | Pagina    | Route      |
      | home      | /home      |
      | dashboard | /dashboard |
