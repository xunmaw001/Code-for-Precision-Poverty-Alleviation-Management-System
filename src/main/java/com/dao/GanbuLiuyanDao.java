package com.dao;

import com.entity.GanbuLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GanbuLiuyanView;

/**
 * 扶贫干部留言 Dao 接口
 *
 * @author 
 */
public interface GanbuLiuyanDao extends BaseMapper<GanbuLiuyanEntity> {

   List<GanbuLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
