package com.douzone.mysite.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.IndexAction;

public class BoardActionFactory extends AbstractActionFactory
{

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action = null;
		
		if ("list".equals(actionName))
		{
			action = new BoardSelectAction();
		}
		else if ("writeform".equals(actionName))
		{
			action = new BoardWriteFormAction();
		}
		else if ("write".equals(actionName))
		{
			action = new BoardWriteAction();
		}
		else if ("view".equals(actionName))
		{
			action = new BoardViewAction();
		}
		else if ("delete".equals(actionName))
		{
			action = new BoardDeleteAction();
		}
		else if ("modifyform".equals(actionName))
		{
			action = new BoardModifyFormAction();
		}
		else if ("modify".equals(actionName))
		{
			action = new BoardModifyAction();
		}
		else if ("replyform".equals(actionName))
		{
			action = new BoardReplyFormAction();
		}
		else if ("reply".equals(actionName))
		{
			action = new BoardReplyAction();
		}
		else if ("comment".equals(actionName))
		{
			action = new BoardCommentAction();
		}
		else if ("commentModifyform".equals(actionName))
		{
			action = new BoardCommentModifyFormAction();
		}
		else if ("commentModify".equals(actionName))
		{
			action = new BoardCommentModifyAction();
		}
		else if ("commentDeleteform".equals(actionName))
		{
			action = new BoardCommentDeleteFormAction();
		}
		else if ("commentDelete".equals(actionName))
		{
			action = new BoardCommentDeleteAction();
		}
		else
		{
			action = new IndexAction();
		}
		return action;
	}
	
}
