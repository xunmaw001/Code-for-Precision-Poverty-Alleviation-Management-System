
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 扶贫干部留言
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ganbuLiuyan")
public class GanbuLiuyanController {
    private static final Logger logger = LoggerFactory.getLogger(GanbuLiuyanController.class);

    private static final String TABLE_NAME = "ganbuLiuyan";

    @Autowired
    private GanbuLiuyanService ganbuLiuyanService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FafangService fafangService;//发放信息
    @Autowired
    private GanbuService ganbuService;//干部
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private HuodongYuyueService huodongYuyueService;//申请信息
    @Autowired
    private XiangmuService xiangmuService;//项目
    @Autowired
    private XiangmuCommentbackService xiangmuCommentbackService;//项目评价
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("干部".equals(role))
            params.put("ganbuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = ganbuLiuyanService.queryPage(params);

        //字典表数据转换
        List<GanbuLiuyanView> list =(List<GanbuLiuyanView>)page.getList();
        for(GanbuLiuyanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GanbuLiuyanEntity ganbuLiuyan = ganbuLiuyanService.selectById(id);
        if(ganbuLiuyan !=null){
            //entity转view
            GanbuLiuyanView view = new GanbuLiuyanView();
            BeanUtils.copyProperties( ganbuLiuyan , view );//把实体数据重构到view中
            //级联表 干部
            //级联表
            GanbuEntity ganbu = ganbuService.selectById(ganbuLiuyan.getGanbuId());
            if(ganbu != null){
            BeanUtils.copyProperties( ganbu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "ganbuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setGanbuId(ganbu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(ganbuLiuyan.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "ganbuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GanbuLiuyanEntity ganbuLiuyan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ganbuLiuyan:{}",this.getClass().getName(),ganbuLiuyan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            ganbuLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("干部".equals(role))
            ganbuLiuyan.setGanbuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        ganbuLiuyan.setCreateTime(new Date());
        ganbuLiuyan.setInsertTime(new Date());
        ganbuLiuyanService.insert(ganbuLiuyan);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GanbuLiuyanEntity ganbuLiuyan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ganbuLiuyan:{}",this.getClass().getName(),ganbuLiuyan.toString());
        GanbuLiuyanEntity oldGanbuLiuyanEntity = ganbuLiuyanService.selectById(ganbuLiuyan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            ganbuLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("干部".equals(role))
//            ganbuLiuyan.setGanbuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        ganbuLiuyan.setUpdateTime(new Date());

            ganbuLiuyanService.updateById(ganbuLiuyan);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GanbuLiuyanEntity> oldGanbuLiuyanList =ganbuLiuyanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ganbuLiuyanService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<GanbuLiuyanEntity> ganbuLiuyanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            GanbuLiuyanEntity ganbuLiuyanEntity = new GanbuLiuyanEntity();
//                            ganbuLiuyanEntity.setGanbuId(Integer.valueOf(data.get(0)));   //干部 要改的
//                            ganbuLiuyanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            ganbuLiuyanEntity.setGanbuLiuyanText(data.get(0));                    //留言内容 要改的
//                            ganbuLiuyanEntity.setInsertTime(date);//时间
//                            ganbuLiuyanEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            ganbuLiuyanEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            ganbuLiuyanEntity.setCreateTime(date);//时间
                            ganbuLiuyanList.add(ganbuLiuyanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        ganbuLiuyanService.insertBatch(ganbuLiuyanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}

