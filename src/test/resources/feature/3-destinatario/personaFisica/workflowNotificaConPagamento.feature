Feature:Controllo dati notifica con pagamento

  @TestSuite

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-ANNULLATA CON PAGAMENTO AFFETTUATO] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "DETN-WPNP-EPNJ-202405-D-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "DETN-WPNP-EPNJ-202405-D-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica annullata persona fisica
    And Si controlla che il testo sia nel box pagamento "//div[@data-testid='cancelledAlertPayment']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-AVVISO PAGO-PA DA PAGARE] Verifica dati  box pagamento
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "XDNJ-ZKJL-DQRZ-202405-X-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "XDNJ-ZKJL-DQRZ-202405-X-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Codice avviso')]"
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Scade il')]"
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='notification-payment-recipient-subtitle']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "JGMW-UHZX-JWXD-202405-P-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "JGMW-UHZX-JWXD-202405-P-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA- F24 COSTI INCLUSI] Verifica testo f24 e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "YKNL-AMKL-UXPD-202405-M-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "YKNL-AMKL-UXPD-202405-M-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='f24-apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA- F24 COSTI NON INCLUSI] Verifica testo f24 e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "GQRP-QZRG-KGZM-202405-E-1 "
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "GQRP-QZRG-KGZM-202405-E-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='f24-apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "HPJD-GRJV-NGDL-202405-A-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "HPJD-GRJV-NGDL-202405-A-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"Almeno un destinatario ha letto la notifica.")] |
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "GKDW-YEZE-DNPL-202405-Z-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "GKDW-YEZE-DNPL-202405-Z-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")] |
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA E F24 COSTI INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "ZVJN-QWEA-GLPY-202405-A-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "ZVJN-QWEA-GLPY-202405-A-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")] |
    And Si controlla che il testo sia nel box pagamento "//p[@data-testid='apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-pagoPA-notice-button']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona fisica


  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MULTIDESTINATARIO- F24 COSTI NON INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "UNUX-TQKU-UDXR-202405-L-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "UNUX-TQKU-UDXR-202405-L-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")] |
    And Si controlla che il testo non sia nel box pagamento "//p[@data-testid='f24-apply-costs-caption']"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MONODESTINATARIO- MULTI AVVISO PAGO-PA] Verifica multi avviso pago pa e click su di esso
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "UKNJ-JHLX-XGHQ-202405-L-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "UKNJ-JHLX-XGHQ-202405-L-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='pay-button'][@disabled]"
    And Si seleziona un avviso pagopa
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='pay-button' and not(@disabled)]"
    And Si controlla che il testo sia nel box pagamento "//button[@data-testid='download-f24-button']"
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-MONODESTINATARIO- AVVISO PAGO-PA] Verifica codice avviso pago-pa notifica pagata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "JGNE-ETKV-HLQG-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "JGNE-ETKV-HLQG-202405-J-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla che il testo sia nel box pagamento "//span[contains(text(),'Codice avviso')]"
    And Logout da portale persona fisica

