package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-27 23:35
 * @Description :
 **/
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid);

    /**
     * 根据cid,start ,pageSize 查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize);
}
