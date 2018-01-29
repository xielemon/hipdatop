package hello;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class Mem {


    public static TopTenMemStore<Date,JSONObject> memStore=new TopTenMemStore<>();


}
