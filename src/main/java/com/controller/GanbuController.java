
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
 * 干部
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ganbu")
public class GanbuController {
    private static final Logger logger = LoggerFactory.getLogger(GanbuController.class);

    private static final String TABLE_NAME = "ganbu";

    @Autowired
    private GanbuService ganbuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FafangService fafangService;//发放信息
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
        CommonUtil.checkMap(params);
        PageUtils page = ganbuService.queryPage(params);

        //字典表数据转换
        List<GanbuView> list =(List<GanbuView>)page.getList();
        for(GanbuView c:list){
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
        GanbuEntity ganbu = ganbuService.selectById(id);
        if(ganbu !=null){
            //entity转view
            GanbuView view = new GanbuView();
            BeanUtils.copyProperties( ganbu , view );//把实体数据重构到view中
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
    public R save(@RequestBody GanbuEntity ganbu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ganbu:{}",this.getClass().getName(),ganbu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<GanbuEntity> queryWrapper = new EntityWrapper<GanbuEntity>()
            .eq("username", ganbu.getUsername())
            .or()
            .eq("ganbu_phone", ganbu.getGanbuPhone())
            .or()
            .eq("ganbu_id_number", ganbu.getGanbuIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GanbuEntity ganbuEntity = ganbuService.selectOne(queryWrapper);
        if(ganbuEntity==null){
            ganbu.setCreateTime(new Date());
            ganbu.setPassword("123456");
            ganbuService.insert(ganbu);
            return R.ok();
        }else {
            return R.error(511,"账户或者干部手机号或者干部身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GanbuEntity ganbu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ganbu:{}",this.getClass().getName(),ganbu.toString());
        GanbuEntity oldGanbuEntity = ganbuService.selectById(ganbu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(ganbu.getGanbuPhoto()) || "null".equals(ganbu.getGanbuPhoto())){
                ganbu.setGanbuPhoto(null);
        }

            ganbuService.updateById(ganbu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GanbuEntity> oldGanbuList =ganbuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ganbuService.deleteBatchIds(Arrays.asList(ids));

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
            List<GanbuEntity> ganbuList = new ArrayList<>();//上传的东西
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
                            GanbuEntity ganbuEntity = new GanbuEntity();
//                            ganbuEntity.setUsername(data.get(0));                    //账户 要改的
//                            ganbuEntity.setPassword("123456");//密码
//                            ganbuEntity.setGanbuName(data.get(0));                    //干部姓名 要改的
//                            ganbuEntity.setGanbuPhone(data.get(0));                    //干部手机号 要改的
//                            ganbuEntity.setGanbuIdNumber(data.get(0));                    //干部身份证号 要改的
//                            ganbuEntity.setGanbuPhoto("");//详情和图片
//                            ganbuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            ganbuEntity.setGanbuEmail(data.get(0));                    //干部邮箱 要改的
//                            ganbuEntity.setCreateTime(date);//时间
                            ganbuList.add(ganbuEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //干部手机号
                                if(seachFields.containsKey("ganbuPhone")){
                                    List<String> ganbuPhone = seachFields.get("ganbuPhone");
                                    ganbuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> ganbuPhone = new ArrayList<>();
                                    ganbuPhone.add(data.get(0));//要改的
                                    seachFields.put("ganbuPhone",ganbuPhone);
                                }
                                //干部身份证号
                                if(seachFields.containsKey("ganbuIdNumber")){
                                    List<String> ganbuIdNumber = seachFields.get("ganbuIdNumber");
                                    ganbuIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> ganbuIdNumber = new ArrayList<>();
                                    ganbuIdNumber.add(data.get(0));//要改的
                                    seachFields.put("ganbuIdNumber",ganbuIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<GanbuEntity> ganbuEntities_username = ganbuService.selectList(new EntityWrapper<GanbuEntity>().in("username", seachFields.get("username")));
                        if(ganbuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GanbuEntity s:ganbuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //干部手机号
                        List<GanbuEntity> ganbuEntities_ganbuPhone = ganbuService.selectList(new EntityWrapper<GanbuEntity>().in("ganbu_phone", seachFields.get("ganbuPhone")));
                        if(ganbuEntities_ganbuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GanbuEntity s:ganbuEntities_ganbuPhone){
                                repeatFields.add(s.getGanbuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [干部手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //干部身份证号
                        List<GanbuEntity> ganbuEntities_ganbuIdNumber = ganbuService.selectList(new EntityWrapper<GanbuEntity>().in("ganbu_id_number", seachFields.get("ganbuIdNumber")));
                        if(ganbuEntities_ganbuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GanbuEntity s:ganbuEntities_ganbuIdNumber){
                                repeatFields.add(s.getGanbuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [干部身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        ganbuService.insertBatch(ganbuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        GanbuEntity ganbu = ganbuService.selectOne(new EntityWrapper<GanbuEntity>().eq("username", username));
        if(ganbu==null || !ganbu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(ganbu.getId(),username, "ganbu", "干部");
        R r = R.ok();
        r.put("token", token);
        r.put("role","干部");
        r.put("username",ganbu.getGanbuName());
        r.put("tableName","ganbu");
        r.put("userId",ganbu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody GanbuEntity ganbu, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<GanbuEntity> queryWrapper = new EntityWrapper<GanbuEntity>()
            .eq("username", ganbu.getUsername())
            .or()
            .eq("ganbu_phone", ganbu.getGanbuPhone())
            .or()
            .eq("ganbu_id_number", ganbu.getGanbuIdNumber())
            ;
        GanbuEntity ganbuEntity = ganbuService.selectOne(queryWrapper);
        if(ganbuEntity != null)
            return R.error("账户或者干部手机号或者干部身份证号已经被使用");
        ganbu.setCreateTime(new Date());
        ganbuService.insert(ganbu);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        GanbuEntity ganbu = ganbuService.selectById(id);
        ganbu.setPassword("123456");
        ganbuService.updateById(ganbu);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        GanbuEntity ganbu = ganbuService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(ganbu.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(ganbu.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        ganbu.setPassword(newPassword);
		ganbuService.updateById(ganbu);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        GanbuEntity ganbu = ganbuService.selectOne(new EntityWrapper<GanbuEntity>().eq("username", username));
        if(ganbu!=null){
            ganbu.setPassword("123456");
            ganbuService.updateById(ganbu);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrGanbu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        GanbuEntity ganbu = ganbuService.selectById(id);
        if(ganbu !=null){
            //entity转view
            GanbuView view = new GanbuView();
            BeanUtils.copyProperties( ganbu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



}

