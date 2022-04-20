package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item> {

	@Autowired
	protected AnyItemListAllService listAllService;

	@Autowired
	protected AnyItemListAllComponentsService listAllComponentsService;

	@Autowired
	protected AnyItemShowService			showService;
	
	@Autowired
	protected AnyItemListService 			listService;

	// Constructors 

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all-tools", "list", this.listAllService);
		super.addCommand("list-all-components", "list", this.listAllComponentsService);
		super.addCommand("list", this.listService);
	}

}