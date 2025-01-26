package com.entity.vo;

import com.entity.FafangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 发放信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fafang")
public class FafangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 发放名称
     */

    @TableField(value = "fafang_name")
    private String fafangName;


    /**
     * 发放编号
     */

    @TableField(value = "fafang_uuid_number")
    private String fafangUuidNumber;


    /**
     * 发放照片
     */

    @TableField(value = "fafang_photo")
    private String fafangPhoto;


    /**
     * 发放类型
     */

    @TableField(value = "fafang_types")
    private Integer fafangTypes;


    /**
     * 金额
     */

    @TableField(value = "fafang_money")
    private Double fafangMoney;


    /**
     * 附件
     */

    @TableField(value = "fafang_file")
    private String fafangFile;


    /**
     * 发放介绍
     */

    @TableField(value = "fafang_content")
    private String fafangContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "fafang_delete")
    private Integer fafangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：发放名称
	 */
    public String getFafangName() {
        return fafangName;
    }


    /**
	 * 获取：发放名称
	 */

    public void setFafangName(String fafangName) {
        this.fafangName = fafangName;
    }
    /**
	 * 设置：发放编号
	 */
    public String getFafangUuidNumber() {
        return fafangUuidNumber;
    }


    /**
	 * 获取：发放编号
	 */

    public void setFafangUuidNumber(String fafangUuidNumber) {
        this.fafangUuidNumber = fafangUuidNumber;
    }
    /**
	 * 设置：发放照片
	 */
    public String getFafangPhoto() {
        return fafangPhoto;
    }


    /**
	 * 获取：发放照片
	 */

    public void setFafangPhoto(String fafangPhoto) {
        this.fafangPhoto = fafangPhoto;
    }
    /**
	 * 设置：发放类型
	 */
    public Integer getFafangTypes() {
        return fafangTypes;
    }


    /**
	 * 获取：发放类型
	 */

    public void setFafangTypes(Integer fafangTypes) {
        this.fafangTypes = fafangTypes;
    }
    /**
	 * 设置：金额
	 */
    public Double getFafangMoney() {
        return fafangMoney;
    }


    /**
	 * 获取：金额
	 */

    public void setFafangMoney(Double fafangMoney) {
        this.fafangMoney = fafangMoney;
    }
    /**
	 * 设置：附件
	 */
    public String getFafangFile() {
        return fafangFile;
    }


    /**
	 * 获取：附件
	 */

    public void setFafangFile(String fafangFile) {
        this.fafangFile = fafangFile;
    }
    /**
	 * 设置：发放介绍
	 */
    public String getFafangContent() {
        return fafangContent;
    }


    /**
	 * 获取：发放介绍
	 */

    public void setFafangContent(String fafangContent) {
        this.fafangContent = fafangContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getFafangDelete() {
        return fafangDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setFafangDelete(Integer fafangDelete) {
        this.fafangDelete = fafangDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
