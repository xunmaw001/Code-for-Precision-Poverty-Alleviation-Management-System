package com.dao;

import com.entity.HuodongYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HuodongYuyueView;

/**
 * 申请信息 Dao 接口
 *
 * @author 
 */
public interface HuodongYuyueDao extends BaseMapper<HuodongYuyueEntity> {

   List<HuodongYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
