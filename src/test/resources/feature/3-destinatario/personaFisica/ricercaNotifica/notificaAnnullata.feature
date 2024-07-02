Feature:Controllo dati notifica annullata


  @ControlloNotificaAnnullata
  Scenario:[NOTIFICA-ANNULLATA] Verifica caratteristiche notifica annullata
  Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
  And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
  And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "DETN-WPNP-EPNJ-202405-D-1"
  And Cliccare sul bottone Filtra persona fisica
  And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "DETN-WPNP-EPNJ-202405-D-1"
  And Cliccare sulla notifica restituita
  Then Si visualizza correttamente la section Dettaglio Notifica annullata persona fisica
  And Logout da portale persona fisica