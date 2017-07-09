package chen.zucc.com.personalassistant.Manage_money_matters;



public class InfoSx {

    private String name;
    private String number;
    private int resIds;

    public InfoSx(String name, String number, int resIds){
        setName(name);
        setNumber(number);
        setResIds(resIds);
    }
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getResIds() {

        return resIds;
    }

    public void setResIds(int resIds) {
        this.resIds = resIds;
    }

}
