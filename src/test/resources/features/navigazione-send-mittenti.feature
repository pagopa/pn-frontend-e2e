@loadPage
Feature: Navigazione portale SEND Mittenti
  In qualità di un utente di una PA
  voglio navigare il portare SEND Mittenti
  e verificare che i dettagli degli elenchi sono raggiungibili

  Scenario Outline: [LOAD_PAGE] Verifica la raggiungibilità delle pagine
    Given l'utente è un "admin" di "Comune di Verona"
    When naviga alla pagina <Pagina>
    Then la pagina deve caricarsi correttamente

    Examples:
      | Pagina          |
      | Dashboard       |
      | APIKey          |
      | NewAPIKey       |
      | Statistics      |
      | PlatformStatus  |

  Scenario Outline: [LOAD_PAGE_DETAILS] Verifica la raggiungibilità delle pagine
    Given l'utente è un "admin" di "Comune di Verona"
    When naviga alla pagina <Lista> e va alla pagina <Dettagli> di dettaglio della prima istanza
    Then la pagina deve caricarsi correttamente

    Examples:
      | Lista     | Dettagli            |
      | Dashboard | NotificationDetails |
      | APIKey    | APIKeyDetails       |