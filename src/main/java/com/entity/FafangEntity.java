package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 发放信息
 *
 * @author 
 * @email
 */
@TableName("fafang")
public class FafangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FafangEntity() {

	}

	public FafangEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 发放名称
     */
    @ColumnInfo(comment="发放名称",type="varchar(200)")
    @TableField(value = "fafang_name")

    private String fafangName;


    /**
     * 发放编号
     */
    @ColumnInfo(comment="发放编号",type="varchar(200)")
    @TableField(value = "fafang_uuid_number")

    private String fafangUuidNumber;


    /**
     * 发放照片
     */
    @ColumnInfo(comment="发放照片",type="varchar(200)")
    @TableField(value = "fafang_photo")

    private String fafangPhoto;


    /**
     * 发放类型
     */
    @ColumnInfo(comment="发放类型",type="int(11)")
    @TableField(value = "fafang_types")

    private Integer fafangTypes;


    /**
     * 金额
     */
    @ColumnInfo(comment="金额",type="decimal(10,2)")
    @TableField(value = "fafang_money")

    private Double fafangMoney;


    /**
     * 附件
     */
    @ColumnInfo(comment="附件",type="varchar(200)")
    @TableField(value = "fafang_file")

    private String fafangFile;


    /**
     * 发放介绍
     */
    @ColumnInfo(comment="发放介绍",type="longtext")
    @TableField(value = "fafang_content")

    private String fafangContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "fafang_delete")

    private Integer fafangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：发放名称
	 */
    public String getFafangName() {
        return fafangName;
    }
    /**
	 * 设置：发放名称
	 */

    public void setFafangName(String fafangName) {
        this.fafangName = fafangName;
    }
    /**
	 * 获取：发放编号
	 */
    public String getFafangUuidNumber() {
        return fafangUuidNumber;
    }
    /**
	 * 设置：发放编号
	 */

    public void setFafangUuidNumber(String fafangUuidNumber) {
        this.fafangUuidNumber = fafangUuidNumber;
    }
    /**
	 * 获取：发放照片
	 */
    public String getFafangPhoto() {
        return fafangPhoto;
    }
    /**
	 * 设置：发放照片
	 */

    public void setFafangPhoto(String fafangPhoto) {
        this.fafangPhoto = fafangPhoto;
    }
    /**
	 * 获取：发放类型
	 */
    public Integer getFafangTypes() {
        return fafangTypes;
    }
    /**
	 * 设置：发放类型
	 */

    public void setFafangTypes(Integer fafangTypes) {
        this.fafangTypes = fafangTypes;
    }
    /**
	 * 获取：金额
	 */
    public Double getFafangMoney() {
        return fafangMoney;
    }
    /**
	 * 设置：金额
	 */

    public void setFafangMoney(Double fafangMoney) {
        this.fafangMoney = fafangMoney;
    }
    /**
	 * 获取：附件
	 */
    public String getFafangFile() {
        return fafangFile;
    }
    /**
	 * 设置：附件
	 */

    public void setFafangFile(String fafangFile) {
        this.fafangFile = fafangFile;
    }
    /**
	 * 获取：发放介绍
	 */
    public String getFafangContent() {
        return fafangContent;
    }
    /**
	 * 设置：发放介绍
	 */

    public void setFafangContent(String fafangContent) {
        this.fafangContent = fafangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getFafangDelete() {
        return fafangDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setFafangDelete(Integer fafangDelete) {
        this.fafangDelete = fafangDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fafang{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", fafangName=" + fafangName +
            ", fafangUuidNumber=" + fafangUuidNumber +
            ", fafangPhoto=" + fafangPhoto +
            ", fafangTypes=" + fafangTypes +
            ", fafangMoney=" + fafangMoney +
            ", fafangFile=" + fafangFile +
            ", fafangContent=" + fafangContent +
            ", fafangDelete=" + fafangDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
