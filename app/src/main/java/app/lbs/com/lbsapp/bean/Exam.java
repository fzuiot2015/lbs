package app.lbs.com.lbsapp.bean;

/**
 * 驾照考题
 */
public class Exam {
    /**
     * 驾照考题id
     */
    private Long id;

    /**
     * 科目类别
     */
    private String subject;

    /**
     * 驾驶证类型
     */
    private String type;

    /**
     * 题目
     */
    private String question;

    /**
     * 选项1
     */
    private String item1;

    /**
     * 选项2
     */
    private String item2;

    /**
     * 选项3
     */
    private String item3;

    /**
     * 选项4
     */
    private String item4;

    /**
     * 答案
     */
    private String answer;

    /**
     * 拓展知识
     */
    private String explains;

    /**
     * 图片url
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}