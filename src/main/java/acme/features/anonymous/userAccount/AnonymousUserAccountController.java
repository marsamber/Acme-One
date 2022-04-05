package acme.features.anonymous.userAccount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Anonymous;

@Controller
public class AnonymousUserAccountController extends AbstractController<Anonymous, UserAccount>  {

	@Autowired
	protected AnonymousUserAccountListAllService		listAllService;

	@Autowired
	protected AnonymousUserAccountShowService			showService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-user-accounts", "list", this.listAllService);
	}
}
