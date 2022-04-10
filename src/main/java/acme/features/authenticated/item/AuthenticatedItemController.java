package acme.features.authenticated.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedItemController extends AbstractController<Authenticated, Item> {

	@Autowired
	protected AuthenticatedItemListAllService listAllService;
	
	@Autowired
	protected AuthenticatedItemListAllComponentsService listAllComponentsService;

	@Autowired
	protected AuthenticatedItemShowService showService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-tools", "list", this.listAllService);
		super.addCommand("list-all-components", "list", this.listAllComponentsService);
	}

}
