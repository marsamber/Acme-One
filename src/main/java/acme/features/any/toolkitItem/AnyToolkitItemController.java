package acme.features.any.toolkitItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.ToolkitItem;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyToolkitItemController extends AbstractController<Any, ToolkitItem> {

	@Autowired
	protected AnyToolkitItemShowService			showService;
	
	@Autowired
	protected AnyToolkitItemListService 			listService;


	// Constructors 


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
	}

}
