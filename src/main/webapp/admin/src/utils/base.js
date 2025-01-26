const base = {
    get() {
        return {
            url : "http://localhost:8080/jingzhunfupinguanlixitong/",
            name: "jingzhunfupinguanlixitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jingzhunfupinguanlixitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "精准扶贫管理系统"
        } 
    }
}
export default base
