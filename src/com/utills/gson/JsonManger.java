package com.utills.gson;

import com.entity.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonManger {

    private final String DATA_PATH = "G:\\project\\Web\\examApplication\\src\\com\\utills\\gson\\datum.json";

    public void saveDataIntoJson(Data data) throws IOException {

        //将Java对象序列化
        Gson gson = new GsonBuilder().create();
        String sets = gson.toJson(data);
        System.out.print(sets);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DATA_PATH), "UTF-8"));

        out.write(sets);
        out.close();

    }

    public Data loadDataFormJson() throws UnsupportedEncodingException {

        String sets = ReadFile(DATA_PATH);

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(sets,Data.class);

    }

    private String ReadFile(String path){
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
            reader = new BufferedReader(isr);

            String tempString = null;
            int line = 1;
            //一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //显示行号
                //System.out.println("line " + line + ": " + tempString);
                laststr = laststr + tempString;
                line ++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }
}