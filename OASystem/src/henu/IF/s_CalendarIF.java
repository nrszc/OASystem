package henu.IF;

import henu.bean.Calendar;

public interface s_CalendarIF {
	public String Json(String employeeID);   //根据员工ID查询日程，在用返回Json字符串
	public String Add(Calendar c);      //增加一条日程记录
	public String Update(Calendar c);   //更新一条日程记录
}
