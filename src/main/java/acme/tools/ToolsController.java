package acme.tools;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class ToolsController extends AbstractController<Authenticated, Item> {
	
	@Autowired
	protected ToolsService	toolsService;
	
	@GetMapping("/tools")
	public ModelAndView tools() {
		ModelAndView result;

		result = new ModelAndView();
		result.setViewName("fragments/tools");
		result.addObject("tools", this.toolsService.findAllItems());
		
		return result;
	}

}
