package henu.IF;

import java.util.List;

import henu.bean.Active;
import henu.bean.Employee;

public interface s_ActivityIF {
	public List<Active> myActivity(String employID); //用返回一个list，用来显示我发起的活动投票的相关信息
	public boolean addActivity(Active a);  //增加一个活动投票
    public boolean deleteActivity(String activeID); //删除有个活动投票
    public List<Active> Activity_part(String employID); //用返回一个list，用来显示我从参与过的活动投票的相关信息
    public List<Active> Activity_unpart(Employee e); //用返回一个list，用来显示我从未参与过的活动投票的相关信息
    public Active Activity_detail(String activeID); //返回一个Active的bean类，用来显示具体的活动投票信息
    public Active Activity_result(Active a); //新插入投票信息，再取出该具体信息，返回一个Active类
}
