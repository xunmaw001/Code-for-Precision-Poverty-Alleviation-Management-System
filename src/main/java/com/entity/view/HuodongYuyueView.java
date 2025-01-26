package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HuodongYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 申请信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("huodong_yuyue")
public class HuodongYuyueView extends HuodongYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 报名状态的值
	*/
	@ColumnInfo(comment="报名状态的字典表值",type="varchar(200)")
	private String huodongYuyueYesnoValue;

	//级联表 干部
		/**
		* 干部姓名
		*/

		@ColumnInfo(comment="干部姓名",type="varchar(200)")
		private String ganbuName;
		/**
		* 干部手机号
		*/

		@ColumnInfo(comment="干部手机号",type="varchar(200)")
		private String ganbuPhone;
		/**
		* 干部身份证号
		*/

		@ColumnInfo(comment="干部身份证号",type="varchar(200)")
		private String ganbuIdNumber;
		/**
		* 干部头像
		*/

		@ColumnInfo(comment="干部头像",type="varchar(200)")
		private String ganbuPhoto;
		/**
		* 干部邮箱
		*/

		@ColumnInfo(comment="干部邮箱",type="varchar(200)")
		private String ganbuEmail;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public HuodongYuyueView() {

	}

	public HuodongYuyueView(HuodongYuyueEntity huodongYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, huodongYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 报名状态的值
	*/
	public String getHuodongYuyueYesnoValue() {
		return huodongYuyueYesnoValue;
	}
	/**
	* 设置： 报名状态的值
	*/
	public void setHuodongYuyueYesnoValue(String huodongYuyueYesnoValue) {
		this.huodongYuyueYesnoValue = huodongYuyueYesnoValue;
	}


	//级联表的get和set 干部

		/**
		* 获取： 干部姓名
		*/
		public String getGanbuName() {
			return ganbuName;
		}
		/**
		* 设置： 干部姓名
		*/
		public void setGanbuName(String ganbuName) {
			this.ganbuName = ganbuName;
		}

		/**
		* 获取： 干部手机号
		*/
		public String getGanbuPhone() {
			return ganbuPhone;
		}
		/**
		* 设置： 干部手机号
		*/
		public void setGanbuPhone(String ganbuPhone) {
			this.ganbuPhone = ganbuPhone;
		}

		/**
		* 获取： 干部身份证号
		*/
		public String getGanbuIdNumber() {
			return ganbuIdNumber;
		}
		/**
		* 设置： 干部身份证号
		*/
		public void setGanbuIdNumber(String ganbuIdNumber) {
			this.ganbuIdNumber = ganbuIdNumber;
		}

		/**
		* 获取： 干部头像
		*/
		public String getGanbuPhoto() {
			return ganbuPhoto;
		}
		/**
		* 设置： 干部头像
		*/
		public void setGanbuPhoto(String ganbuPhoto) {
			this.ganbuPhoto = ganbuPhoto;
		}

		/**
		* 获取： 干部邮箱
		*/
		public String getGanbuEmail() {
			return ganbuEmail;
		}
		/**
		* 设置： 干部邮箱
		*/
		public void setGanbuEmail(String ganbuEmail) {
			this.ganbuEmail = ganbuEmail;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "HuodongYuyueView{" +
			", huodongYuyueYesnoValue=" + huodongYuyueYesnoValue +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", ganbuName=" + ganbuName +
			", ganbuPhone=" + ganbuPhone +
			", ganbuIdNumber=" + ganbuIdNumber +
			", ganbuPhoto=" + ganbuPhoto +
			", ganbuEmail=" + ganbuEmail +
			"} " + super.toString();
	}
}
