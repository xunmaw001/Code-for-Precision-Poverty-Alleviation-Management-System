
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
 * 发放信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fafang")
public class FafangController {
    private static final Logger logger = LoggerFactory.getLogger(FafangController.class);

    private static final String TABLE_NAME = "fafang";

    @Autowired
    private FafangService fafangService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GanbuService ganbuService;//干部
    @Autowired
    private GanbuLiuyanService ganbuLiuyanService;//扶贫干部留言
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
        params.put("fafangDeleteStart",1);params.put("fafangDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = fafangService.queryPage(params);

        //字典表数据转换
        List<FafangView> list =(List<FafangView>)page.getList();
        for(FafangView c:list){
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
        FafangEntity fafang = fafangService.selectById(id);
        if(fafang !=null){
            //entity转view
            FafangView view = new FafangView();
            BeanUtils.copyProperties( fafang , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(fafang.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody FafangEntity fafang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fafang:{}",this.getClass().getName(),fafang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            fafang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<FafangEntity> queryWrapper = new EntityWrapper<FafangEntity>()
            .eq("yonghu_id", fafang.getYonghuId())
            .eq("fafang_name", fafang.getFafangName())
            .eq("fafang_types", fafang.getFafangTypes())
            .eq("fafang_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FafangEntity fafangEntity = fafangService.selectOne(queryWrapper);
        if(fafangEntity==null){
            fafang.setFafangDelete(1);
            fafang.setInsertTime(new Date());
            fafang.setCreateTime(new Date());
            fafangService.insert(fafang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FafangEntity fafang, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fafang:{}",this.getClass().getName(),fafang.toString());
        FafangEntity oldFafangEntity = fafangService.selectById(fafang.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            fafang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(fafang.getFafangPhoto()) || "null".equals(fafang.getFafangPhoto())){
                fafang.setFafangPhoto(null);
        }
        if("".equals(fafang.getFafangFile()) || "null".equals(fafang.getFafangFile())){
                fafang.setFafangFile(null);
        }

            fafangService.updateById(fafang);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FafangEntity> oldFafangList =fafangService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<FafangEntity> list = new ArrayList<>();
        for(Integer id:ids){
            FafangEntity fafangEntity = new FafangEntity();
            fafangEntity.setId(id);
            fafangEntity.setFafangDelete(2);
            list.add(fafangEntity);
        }
        if(list != null && list.size() >0){
            fafangService.updateBatchById(list);
        }

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
            List<FafangEntity> fafangList = new ArrayList<>();//上传的东西
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
                            FafangEntity fafangEntity = new FafangEntity();
//                            fafangEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            fafangEntity.setFafangName(data.get(0));                    //发放名称 要改的
//                            fafangEntity.setFafangUuidNumber(data.get(0));                    //发放编号 要改的
//                            fafangEntity.setFafangPhoto("");//详情和图片
//                            fafangEntity.setFafangTypes(Integer.valueOf(data.get(0)));   //发放类型 要改的
//                            fafangEntity.setFafangMoney(data.get(0));                    //金额 要改的
//                            fafangEntity.setFafangFile(data.get(0));                    //附件 要改的
//                            fafangEntity.setFafangContent("");//详情和图片
//                            fafangEntity.setFafangDelete(1);//逻辑删除字段
//                            fafangEntity.setInsertTime(date);//时间
//                            fafangEntity.setCreateTime(date);//时间
                            fafangList.add(fafangEntity);


                            //把要查询是否重复的字段放入map中
                                //发放编号
                                if(seachFields.containsKey("fafangUuidNumber")){
                                    List<String> fafangUuidNumber = seachFields.get("fafangUuidNumber");
                                    fafangUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fafangUuidNumber = new ArrayList<>();
                                    fafangUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fafangUuidNumber",fafangUuidNumber);
                                }
                        }

                        //查询是否重复
                         //发放编号
                        List<FafangEntity> fafangEntities_fafangUuidNumber = fafangService.selectList(new EntityWrapper<FafangEntity>().in("fafang_uuid_number", seachFields.get("fafangUuidNumber")).eq("fafang_delete", 1));
                        if(fafangEntities_fafangUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FafangEntity s:fafangEntities_fafangUuidNumber){
                                repeatFields.add(s.getFafangUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [发放编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fafangService.insertBatch(fafangList);
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

