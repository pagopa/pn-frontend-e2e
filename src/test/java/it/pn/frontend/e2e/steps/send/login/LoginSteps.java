package it.pn.frontend.e2e.steps.send.login;


import io.cucumber.java.en.Given;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.pn.frontend.e2e.enums.User;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.model.SharedContext;
import it.pn.frontend.e2e.steps.send.login.page.LoginPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginSteps {

    private final WebPresentationGateway uiGateway;
    private final SharedContext sharedContext;

    @Given("l'utente tenta di navigare alla pagina {page}")
    public void loadPage(PageInfo page) {
        //presentation.navigateTo(page.url());
        //sharedContext.setCurrentPage(page);
    }

//    @Given("l'utente {user} tenta la login")
//    public void compileForm(User user) {
//        LoginPage loginPage = (LoginPage) presentation.parse(sharedContext.getCurrentPage().pageClass());
//        loginPage.username().set(user.getUsername());
//        loginPage.password().set(user.getPassword());
//        loginPage.comune().set(user.getComune());
//        loginPage.login();
//    }

    @Given("l'utente {user} tenta la login nella pagina {page}")
    public void login(User user, PageInfo pageInfo) {
        LoginPage loginPage = uiGateway.bind(LoginPage.class);
        loginPage.form().get();
//        LoginPage loginPage = (LoginPage) presentation.bind(pageInfo.pageClass());
//        loginPage.form().username().set(user.getUsername());
//        loginPage.form().password().set(user.getPassword());
//        loginPage.form().comune().set(user.getComune());
//        loginPage.form().submit();
    }

//    @Given("l'utente compila la form {String} con:")
//    public void compileForm(UiElemetInfo formInfo, DataTable dataTable) {
//        Form form = (Form) presentation.bind(formInfo.domModel());
//        form.fill(dataTable.asMap());
//        //TODO: assert che garantisce la corretta compilazione
//    }
}
