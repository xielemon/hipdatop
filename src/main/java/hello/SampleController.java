package hello;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@EnableAutoConfiguration
public class SampleController {

    private static Logger logger= LoggerFactory.getLogger(SampleController.class);

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static Mem mem=new Mem();

    private static long THIRTY_MIN=30*60*1000;


    @Autowired
    private KafkaTemplate<String, String> template;


    @RequestMapping("/getTopTen.do")
    @ResponseBody
    String home(String time) {

        Date date=new Date(Long.parseLong(time)-Long.parseLong(time)%THIRTY_MIN-THIRTY_MIN);

        if(mem.memStore.containsKey(date)){
            return mem.memStore.get(date).toJSONString();
        }
        else{
            return "Hello World!";
        }


    }


    @KafkaListener(id = "12345", topics = "topTen201")
    public void listenT1(ConsumerRecord<?, String> cr) throws Exception {
//        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
        JSONObject object=JSON.parseObject(cr.value());
        logger.info("{}",object);
        Date date= (Date) sdf.parseObject(object.getString("time"));
        mem.memStore.put(date,object);
    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}