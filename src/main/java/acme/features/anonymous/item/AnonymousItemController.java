package acme.features.anonymous.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Anonymous;

@Controller
public class AnonymousItemController extends AbstractController<Anonymous, Item> {

	@Autowired
	protected AnonymousItemListAllService		listAllService;

	@Autowired
	protected AnonymousItemShowService			showService;
	
	@Autowired
	protected AnonymousItemListService 			listService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("list-all-tools", "list", this.listAllService);
	}

}
