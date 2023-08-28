Feature:Il delegato persona giuridica annulLa l'operazione di rifiuto delega
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "delegatoPG" portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente


  Scenario: Il delegato persona giuridica annulLa l'operazione di rifiuto delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delegaPG"
    And Nella sezione Deleghe si clicca sul menu della delega
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone  anulla
    And Si controlla che la delega a lo stato Attiva "personaGiuridica"
    And Logout da portale persona giuridica