Feature: PG - Rotazione di una virtual key attiva per un utente Amministratore Persona Giuridica
@TestSuite
@TA_PG_RotazioneDiUnaVirtualKeyAttiva_QA_5320

Scenario:PN-QA-5320  PG - Rotazione di una virtual key attiva per un utente Amministratore Persona Giuridica
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
  And Verifica testo nel pop-up "La tua chiave personale"
  And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
  And Verifica testo nel pop-up "Ok, ho capito"
  And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
  And Verifica stato Chiave Personale "Attiva"

  And Cliccare sui tre puntini Virtual key con stato "Attiva"
  When verifica tre puntini mostra di piu
    | ruota        | Ruota |
    | blocca       | Blocca |
    | view         | Visualizza codice |
  And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
  And Nella pop up cliccare sul tasto conferma
  Then Verifica stato Chiave Personale "Attiva"
  And Verifica stato Chiave Personale "Ruotata"













