package acme.features.any.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyToolkitController extends AbstractController<Any, Toolkit> {

	@Autowired
	protected AnyToolkitListAllService		listAllService;
	
	@Autowired
	protected AnyToolkitListByItemService	listByItemService;

	@Autowired
	protected AnyToolkitShowService			showService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-toolkits", "list", this.listAllService);
		super.addCommand("list-by-item", "list", this.listByItemService);
	}

}
