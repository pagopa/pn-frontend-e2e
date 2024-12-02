Feature:Controllo dati notifica con pagamento

  @TestSuite
  @PG
  @NotificaConPagamentoPG


  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-ANNULLATA CON PAGAMENTO AFFETTUATO] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "LZXM-LNKJ-PTMR-202405-K-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | LZXM-LNKJ-PTMR-202405-K-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica annullata
    And Si controlla che il testo sia nel box pagamento "//div[@data-testid='cancelledAlertPayment']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-AVVISO PAGO-PA DA PAGARE] Verifica dati  box pagamento
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "UREL-JUHA-UPUA-202405-D-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | UREL-JUHA-UPUA-202405-D-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Codice avviso')]"
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Scade il')]"
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='notification-payment-recipient-subtitle']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "LDTY-YKMT-NVTD-202405-L-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | LDTY-YKMT-NVTD-202405-L-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "QMUM-VKVL-NVXV-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | QMUM-VKVL-NVXV-202405-J-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA- F24 COSTI NON INCLUSI] Verifica testo f24 e notifica senza costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "RVTW-VWTG-VMHM-202405-G-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | RVTW-VWTG-VMHM-202405-G-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='f24-apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "KRUD-XMTW-RANZ-202405-R-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | KRUD-XMTW-RANZ-202405-R-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"Almeno un destinatario ha letto la notifica.")] |
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "WKED-MRVH-JKUY-202405-R-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | WKED-MRVH-JKUY-202405-R-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")] |
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA E F24 COSTI INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica con costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "JRNE-EZAY-DXGZ-202405-R-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | JRNE-EZAY-DXGZ-202405-R-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"Almeno un destinatario ha letto la notifica")] |
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MONODESTINATARIO- AVVISO PAGO-PA E F24 COSTI INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica con costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "LEHT-MGMP-HUQN-202405-Y-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | LEHT-MGMP-HUQN-202405-Y-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona giuridica


  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MULTIDESTINATARIO- F24 COSTI NON INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica senza costi inclusi
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "PDAL-DPWV-AVDN-202405-P-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | PDAL-DPWV-AVDN-202405-P-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"Almeno un destinatario ha letto la notifica.")] |
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='f24-apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MONODESTINATARIO- MULTI AVVISO PAGO-PA] Verifica multi avviso pago pa e click su di esso
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "ENRN-PAEY-LTYA-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | ENRN-PAEY-LTYA-202405-J-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='pay-button'][@disabled]"
    And Si seleziona un avviso pagopa
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='pay-button' and not(@disabled)]"
    #And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona giuridica

  @ControlloNotificaConPagamentoPG
  Scenario:[NOTIFICA-MONODESTINATARIO- AVVISO PAGO-PA] Verifica codice avviso pago-pa notifica pagata
    Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "NRYX-HPNA-WTAM-202405-D-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona giuridica vengo restituite tutte le notifiche con il codice IUN della notifica
      | iun            | NRYX-HPNA-WTAM-202405-D-1 |
      | ragioneSociale | Convivio Spa              |
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Codice avviso')]"
    And Logout da portale persona giuridica

