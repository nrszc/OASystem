package henu.factory;

import henu.IF.LoginIF;
import henu.impl.LoginImpl;

public class DaoFactory {
	public static LoginIF getLoginInstance()
	{
		return new LoginImpl();
	}
}
