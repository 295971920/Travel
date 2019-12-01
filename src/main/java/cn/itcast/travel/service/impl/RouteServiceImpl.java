package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-27 23:50
 * @Description :
 **/
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    /**
     * 根据累呗进行分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置单前页面码
        pb.setCurrentPage(currentPage);
        //设置每一页显示的条数
        pb.setPageSize(pageSize);

        //设置总数记录数
        int totalCount = routeDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage-1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid, start, pageSize);
        pb.setList(list);

        //设置总页数= 总记录数/每页显示的条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }
}











