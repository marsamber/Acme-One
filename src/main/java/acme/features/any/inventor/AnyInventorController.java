package acme.features.any.inventor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;
import acme.roles.Inventor;

@Controller
public class AnyInventorController extends AbstractController<Any, Inventor> {

	@Autowired
	protected AnyInventorListAllService		listAllService;

	@Autowired
	protected AnyInventorShowService			    showService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-inventors", "list", this.listAllService);
	}

}
