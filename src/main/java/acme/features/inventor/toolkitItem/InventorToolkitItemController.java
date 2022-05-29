package acme.features.inventor.toolkitItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.ToolkitItem;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitItemController extends AbstractController<Inventor, ToolkitItem> {

	@Autowired
	protected InventorToolkitItemCreateService 			createService;
	
	@Autowired
	protected InventorToolkitItemUpdateService			updateService;
	
	@Autowired
	protected InventorToolkitItemDeleteService			deleteService;
	
	@Autowired
	protected InventorToolkitItemShowService			showService;
	
	@Autowired
	protected InventorToolkitItemListService 			listService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
	}

}
