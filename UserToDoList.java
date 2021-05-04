public class UserToDoList {
     boolean FinishIns;//是否已完成
     String Content;//待办内容
     int index;//编号

    public String getContent() {
        return Content;
    }
    public boolean getFinishIns(){
        return FinishIns;
    }

    public void setFinishIns(boolean finishIns) {
        FinishIns = finishIns;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
