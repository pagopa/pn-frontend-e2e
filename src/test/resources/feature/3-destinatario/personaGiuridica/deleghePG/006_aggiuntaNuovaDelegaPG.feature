Feature: La persona giuridica aggiunge una nuova delega

  @TestSuite
  @TA_PGaggiungiAltraNuovaDelega
  @DeleghePG
  @PG

  Scenario: PN-9165 - La persona giuridica aggiunge una nuova delega
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica_1"
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Si controlla che non sia presente una delega con stesso nome "nuovaDelegaPG" persona giuridica
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati "nuovaDelegaPG"
    And Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta
    And Nella sezione Aggiungi Delega persona giuridica salvare il codice verifica all'interno del file "nuovaDelegaPG"
    And Nella sezione Aggiungi Delega  persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma
    And Logout da portale persona giuridica
