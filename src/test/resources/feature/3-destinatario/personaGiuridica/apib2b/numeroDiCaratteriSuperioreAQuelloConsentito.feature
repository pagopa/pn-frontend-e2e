Feature: PG -Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito

@TA_PG_NumeroDiCampiSuperiori_QA_5307

Scenario:PN-QA-5307  PG - Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito
  Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
#  When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
#  And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
#  And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
#  And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
#  | nome        | Chiave- |
#  | publicKey   | aa |
#  And Cliccare su registra
#  And Si visualizza correttamente la sezione Ottieni Parametri
#  And Si copia correttamente il campo KID cliccando sul bottone di copia
#  And Si copia correttamente il campo Issuer cliccando sul bottone di copia
#  And Cliccare su registra
#  Then Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica
#  And Si controlla che il pulsante Genera chiave pubblica non sia più presente nella pagina Integrazione API
#  And Logout da portale persona giuridica