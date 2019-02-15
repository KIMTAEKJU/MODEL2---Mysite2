package com.douzone.mysite.action.guestbook;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;

public class GuestBookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action = null;
		
		if ("list".equals(actionName))
			action = new GuestBookSelectAction();
		
		else if ("insert".equals(actionName))
			action = new GuestBookInsertAction();
		
		else if("deleteform".equals(actionName))
			action = new GuestBookDeleteFormAction();

		else if("delete".equals(actionName))
			action = new GuestBookDeleteAction();
		
		else if("ajax".equals(actionName))
			action = new AjaxAction();
		
		else if("ajax-list".equals(actionName))
			action = new AjaxListAction();
		
		else if("ajax-delete".equals(actionName))
			action = new AjaxDeleteAction();
		else if("ajax-insert".equals(actionName))
			action = new AjaxInsertAction();
		else
			action = new GuestBookSelectAction();
		
		return action;
	}

}
