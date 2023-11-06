Feature: Mittente effetua una ricerca notifiche per CF sbagliato
  #Mittente loggato effettua una ricerca

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente


  @inserimentoCodiceFiscaleSbagliato


  Scenario: Mittente loggato effettua una ricerca per CF sbagliato
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale sbagliato ""
    Then Nella pagina Piattaforma Notifiche si controlla che si visualizza il messaggio di errore codice fiscale
    And Nella pagina Piattaforma Notifiche si controlla che il bottone Filtra non sia attivo
    And Logout da portale mittente