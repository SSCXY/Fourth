import java.io.*;
import java.util.*;
public class Auto_txt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String UserInput = input.nextLine();//存放用户输入的字符串
        String AfterSplitStr[] = new String[3];
        AfterSplitStr = UserInput.split(" ");//分割用户输入的字符串
        String [] strArr = new String [500];
        int count = 0;
        try {
            String pathname = "src/" + AfterSplitStr[0];
            File filename = new File(pathname); // 读取以上路径的txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "GBK"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            while (line != null) {
                strArr[count] = line;
                count++;
                line = br.readLine(); // 一次读入一行数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i <= count - 1; i++){
            if (strArr[i] != null){
                strArr[i] = strArr[i].replaceAll("\\s+"," ");

            }
        }
        Infomation[] provinces = new Infomation[count];
        for(int i = 0; i <= count - 1; i++){
            String [] str = new String[3];
            str = strArr[i].split(" ");
            provinces[i] = new Infomation(str[0],str[1],str[2]);
//            System.out.println(provinces[i]);
        }
        //剔除无效数据后存到list中，list的个数就是有效数据的个数
        List<Infomation> list = new ArrayList<>();
        for(int i = 0; i <= count - 1; i++){
            if (provinces[i].getNum().equals("0")) continue;
            else list.add(provinces[i]);
        }
        List<LastInfo> lastInfoList = new ArrayList<>();
//每个省份对应有多少个的数组
        int pro_num[] = new int[15];//用于统计省份的数组
        int sum = -1;//用于统计每个省份有多少的中间变量
        for(int i = 0,j = 0; i <= list.size() - 1 ; i++){
            if(i == list.size() - 1){
                sum++;
                pro_num[j] = sum + 1;
            }
            if( i == 0 || list.get(i).getProvince().equals(list.get(i-1).getProvince()) ){
                sum++;
            }
            else {
                pro_num[j] = sum + 1;
                sum = 0;
                j++;
            }
        }
        System.out.println(Arrays.toString(pro_num));
        int sum_pro = 0;//一共有多少个省份
        for (int i = 0; i <= 9; i++){
            if (pro_num[i] != 0) sum_pro++;
        }
        System.out.println(sum_pro);
//i是一共有多少个省份-1，j是每个省份对应有多少个-1 k是所有数据的数量-1
        for(int i = 0,k = 0; i <= sum_pro - 1 ; i++){
            for (int j = 0; j <= pro_num[i] - 1; j++){
                LastInfo lastInfo = new LastInfo(list.get(k).getProvince(), pro_num[i], list.get(k).getArea(), Integer.parseInt(list.get(k).getNum()));
                lastInfoList.add(lastInfo);
                k++;
            }
        }

        Comparator<LastInfo> byPronum = Comparator.comparing(LastInfo::getProvince_num).reversed();
        Comparator<LastInfo> byProname = Comparator.comparing(LastInfo::getProvince);
        Comparator<LastInfo> byAreanum = Comparator.comparing(LastInfo::getArea_num).reversed();
        Comparator<LastInfo> byAreaname = Comparator.comparing(LastInfo::getArea);
        lastInfoList.sort(byPronum.thenComparing(byProname.thenComparing(byAreanum.thenComparing(byAreaname))));

        for(int i = 0; i < lastInfoList.size(); i++){
            System.out.println(lastInfoList.get(i).toString());
        }
           try {
               FileWriter filewriter = new FileWriter("src/" + AfterSplitStr[1]);
               for(int i = 0; i < lastInfoList.size(); i++){
                   if(AfterSplitStr.length == 2){


                       if(i == 0)
                           filewriter.write(lastInfoList.get(0).getProvince() + "\t" + lastInfoList.get(0).getProvince_num()+ "个城市"+ "\r\n"
                                   + lastInfoList.get(0).getArea() + "\t" + lastInfoList.get(0).getArea_num() + "\r\n");
                       else if(lastInfoList.get(i).getProvince().equals( lastInfoList.get(i - 1).getProvince())){
                           filewriter.write(lastInfoList.get(i).getArea() + "\t"
                                   + lastInfoList.get(i).getArea_num() + "\r\n");
                       }else {
                           filewriter.write( "\r\n" + lastInfoList.get(i).getProvince() + "\t" + lastInfoList.get(i).getProvince_num() + "个城市" +"\r\n"
                                   + lastInfoList.get(i).getArea() + "\t" + lastInfoList.get(i).getArea_num() + "\r\n");
                       }
                   }else {
                       if(lastInfoList.get(i).getArea_num().equals("0") ||(! lastInfoList.get(i).getProvince().equals(AfterSplitStr[2])))continue;
                       else if(i == 0)
                           filewriter.write(lastInfoList.get(i).getProvince() + lastInfoList.get(i).getProvince_num() +"个城市" +"\r\n"
                                   + lastInfoList.get(i).getArea() + "\t" + lastInfoList.get(i).getArea_num() + "\r\n");
                       else if(lastInfoList.get(i).getProvince().equals( lastInfoList.get(i - 1).getProvince())){
                           filewriter.write(lastInfoList.get(i).getArea() + "\t"
                                   + lastInfoList.get(i).getArea_num() + "\r\n");
                       }else {
                           filewriter.write(lastInfoList.get(i).getProvince()+ "\t" + lastInfoList.get(i).getProvince_num() +"个城市" + "\r\n"
                                   + lastInfoList.get(i).getArea() + "\t" + lastInfoList.get(i).getArea_num() + "\r\n");
                       }
                   }
               }
               filewriter.flush();
               filewriter.close();
           }catch (IOException e){
               e.printStackTrace();
           }
       }
}