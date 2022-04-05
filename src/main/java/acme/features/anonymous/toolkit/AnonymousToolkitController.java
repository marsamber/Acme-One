package acme.features.anonymous.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Anonymous;

@Controller
public class AnonymousToolkitController extends AbstractController<Anonymous, Toolkit> {

	@Autowired
	protected AnonymousToolkitListAllService		listAllService;
	
	@Autowired
	protected AnonymousToolkitListByItemService		listByItemService;

	@Autowired
	protected AnonymousToolkitShowService			showService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-toolkits", "list", this.listAllService);
		super.addCommand("list-by-item", "list", this.listByItemService);
	}

}
