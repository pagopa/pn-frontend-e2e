Feature: Il delegato persona giuridica modifica una delega non assegnandoli un gruppo
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "delegatoPG" portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente


  Scenario: Il delegato persona giuridica modifica una delega non assegnandoli un gruppo
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delegaPG"
    And Nella sezione Deleghe si clicca sul menu della delega
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone non assegna a un gruppo
    And Si clicca su conferma
    And Si controlla che la delega a lo stato Attiva "personaGiuridica"
    And Logout da portale persona giuridica