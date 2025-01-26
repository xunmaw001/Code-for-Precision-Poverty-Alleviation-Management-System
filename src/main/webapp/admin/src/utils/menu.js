const menu = {
    list() {
        return [
    {
        "backMenu":[
            {
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"管理员管理",
                        "menuJump":"列表",
                        "tableName":"users"
                    }
                ],
                "menu":"管理员管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"干部管理",
                        "menuJump":"列表",
                        "tableName":"ganbu"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "修改",
                            "删除"
                        ],
                        "menu":"扶贫干部留言管理",
                        "menuJump":"列表",
                        "tableName":"ganbuLiuyan"
                    }
                ],
                "menu":"干部管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"用户管理",
                        "menuJump":"列表",
                        "tableName":"yonghu"
                    }
                ],
                "menu":"用户管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"发放信息管理",
                        "menuJump":"列表",
                        "tableName":"fafang"
                    }
                ],
                "menu":"发放信息管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"公告管理",
                        "menuJump":"列表",
                        "tableName":"gonggao"
                    }
                ],
                "menu":"公告管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"申请信息管理",
                        "menuJump":"列表",
                        "tableName":"huodongYuyue"
                    }
                ],
                "menu":"申请信息管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"项目管理",
                        "menuJump":"列表",
                        "tableName":"xiangmu"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "修改",
                            "删除"
                        ],
                        "menu":"项目评价管理",
                        "menuJump":"列表",
                        "tableName":"xiangmuCommentback"
                    }
                ],
                "menu":"项目管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"发放类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryFafang"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"公告类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryGonggao"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"项目类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryXiangmu"
                    }
                ],
                "menu":"基础数据管理"
            }
            /*,{
			    "child":[
			        {
			            "buttons":[
			                "查看"
			            ],
			            "menu":"数据备份",
			            "menuJump":"列表",
			            "tableName":"beifen"
			        },
					{
					    "buttons":[
					        "查看"
					    ],
					    "menu":"数据还原",
					    "menuJump":"列表",
					    "tableName":"huanyuan"
					}
			    ],
			    "menu":"数据库管理"
			}*/
        ],
        "frontMenu":[],
        "hasBackLogin":"是",
        "hasBackRegister":"否",
        "hasFrontLogin":"否",
        "hasFrontRegister":"否",
        "roleName":"管理员",
        "tableName":"users"
    },
            {
                "backMenu":[
                   {
                        "child":[

                            {
                                "buttons":[
                                    "查看",
                                    "修改",
                                    "删除"
                                ],
                                "menu":"咨询信息",
                                "menuJump":"列表",
                                "tableName":"ganbuLiuyan"
                            }
                        ],
                        "menu":"咨询信息"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                    "新增",
                                    "修改",
                                ],
                                "menu":"用户管理",
                                "menuJump":"列表",
                                "tableName":"yonghu"
                            }
                        ],
                        "menu":"用户管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                    "新增",
                                ],
                                "menu":"发放信息管理",
                                "menuJump":"列表",
                                "tableName":"fafang"
                            }
                        ],
                        "menu":"发放信息管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"公告管理",
                                "menuJump":"列表",
                                "tableName":"gonggao"
                            }
                        ],
                        "menu":"公告管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                    "新增",
                                    "修改",
                                    "审核"
                                ],
                                "menu":"申请信息管理",
                                "menuJump":"列表",
                                "tableName":"huodongYuyue"
                            }
                        ],
                        "menu":"申请信息管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"项目管理",
                                "menuJump":"列表",
                                "tableName":"xiangmu"
                            }
                            ,
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"项目评价管理",
                                "menuJump":"列表",
                                "tableName":"xiangmuCommentback"
                            }
                        ],
                        "menu":"项目管理"
                    }
                    /*,{
                        "child":[
                            {
                                "buttons":[
                                    "查看"
                                ],
                                "menu":"数据备份",
                                "menuJump":"列表",
                                "tableName":"beifen"
                            },
                            {
                                "buttons":[
                                    "查看"
                                ],
                                "menu":"数据还原",
                                "menuJump":"列表",
                                "tableName":"huanyuan"
                            }
                        ],
                        "menu":"数据库管理"
                    }*/
                ],
                "frontMenu":[],
                "hasBackLogin":"是",
                "hasBackRegister":"否",
                "hasFrontLogin":"否",
                "hasFrontRegister":"否",
                "roleName":"扶贫干部",
                "tableName":"ganbu"
            },

            {
                "backMenu":[
                    {
                        "child":[

                            {
                                "buttons":[
                                    "查看",
                                    "新增"
                                ],
                                "menu":"咨询信息",
                                "menuJump":"列表",
                                "tableName":"ganbuLiuyan"
                            }
                        ],
                        "menu":"咨询信息"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"发放信息管理",
                                "menuJump":"列表",
                                "tableName":"fafang"
                            }
                        ],
                        "menu":"发放信息管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"公告管理",
                                "menuJump":"列表",
                                "tableName":"gonggao"
                            }
                        ],
                        "menu":"公告管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                    "新增",
                                ],
                                "menu":"申请信息管理",
                                "menuJump":"列表",
                                "tableName":"huodongYuyue"
                            }
                        ],
                        "menu":"申请信息管理"
                    }
                    ,{
                        "child":[
                            {
                                "buttons":[
                                    "查看",
                                ],
                                "menu":"项目管理",
                                "menuJump":"列表",
                                "tableName":"xiangmu"
                            }
                            ,
                            {
                                "buttons":[
                                    "查看",
                                    "新增"
                                ],
                                "menu":"项目评价管理",
                                "menuJump":"列表",
                                "tableName":"xiangmuCommentback"
                            }
                        ],
                        "menu":"项目管理"
                    }
                    /*,{
                        "child":[
                            {
                                "buttons":[
                                    "查看"
                                ],
                                "menu":"数据备份",
                                "menuJump":"列表",
                                "tableName":"beifen"
                            },
                            {
                                "buttons":[
                                    "查看"
                                ],
                                "menu":"数据还原",
                                "menuJump":"列表",
                                "tableName":"huanyuan"
                            }
                        ],
                        "menu":"数据库管理"
                    }*/
                ],
                "frontMenu":[],
                "hasBackLogin":"是",
                "hasBackRegister":"否",
                "hasFrontLogin":"否",
                "hasFrontRegister":"否",
                "roleName":"贫困户",
                "tableName":"yonghu"
            }
]
    }
}
export default menu;
