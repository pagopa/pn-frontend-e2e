Feature: PG - Eliminazione di una virtual key ruotata per un utente Amministratore Persona Giuridica
@TestSuite
@TA_PG_EliminazioneDiUnaVirtualKeyRuotata_QA_5322

Scenario:PN-QA-5322  PG - Eliminazione di una virtual key ruotata per un utente Amministratore Persona Giuridica
  Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
  When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
  And Pulisci ambiente public keys
# tasto Registra chiave pubblica
  And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
  And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
  And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
    | nome        | Chiave- |
  And Cliccare su registra
  And Si visualizza correttamente la sezione Ottieni Parametri
  And Cliccare su registra

 And Pulisci ambiente virtual keys

  When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
  And Click su tasto Genera Chiave Personale
  And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
  And Verifica stato Chiave Personale "Attiva"
# ruoto la key
  And Cliccare sui tre puntini Virtual key con stato "Attiva"
  And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
  And Nella pop up cliccare sul tasto conferma
  And Cliccare sui tre puntini Virtual key con stato "Ruotata"
  Then verifica tre puntini mostra di piu
    | delete       | Elimina |
    | view         | Visualizza codice |
  And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
  And Nella pop up cliccare sul tasto conferma














