package com.Marissa.Demo4TW;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class SellingByCategoryDiscount {

    //Step1 scanner get the barcode and count the product by barcode at the same time
    public Map InputBarcode(String[] barcode) {
        Map<String, Integer> itemcnt = new HashMap<String, Integer>();
        Integer addcnt = 1;
        for(String singlecode:barcode) {
            putMapData(itemcnt, singlecode, addcnt);
        }
        return itemcnt;
    }

    //the way to save the count of the product on the bill (hashmap<barcode,count>)
    public void putMapData(Map<String, Integer> m, String s, Integer cnt){
        if(isSingle(s)){
            if(m.get(s)!= null){
                cnt = (Integer)m.get(s) + (Integer)1;
                m.put(s, cnt);
            }else{
                m.put(s, cnt);
            }
        }else{
            if(m.get(s)!= null){
                cnt = (Integer)m.get(s.substring(0, 10)) + getCntFromString(s);
                m.put(s, cnt);
            }else{
                m.put(s.substring(0, 10), getCntFromString(s));
            }
        }
    }

    //regular the length of barcode is 10 or find the "-" to separate the code and count
    //judge the barcode
    public boolean isSingle(String str) {
        if(str.length()==10){
            return true;
        }
        return false;
    }

    //get the numberString after the barcode
    public Integer getCntFromString(String str) {
        Integer cnt = 1;
        if(str.length() > 11){
            String s = str.substring(11);
            if(s.length() == 0) {
                return cnt;
            }else {
                cnt = (Integer)ConvertStringToInteger(s);
            }
        }else if(str.length() < 10){
            cnt = 0;
            return cnt;
        }
        return cnt;
    }

    //convert substring to int data(item count)
    public Integer ConvertStringToInteger(String icnt) {
        Integer cnt = 0;
        char[] convert = icnt.toCharArray();
        if(icnt == null ||icnt.equals("")||convert[0]<'0'||convert[0]>'9'){
            return 1;
        }
        for(char s:convert){
            if(s>='0'&&s<='9'){
                cnt = cnt*10+(int)(s-'0');
            }else {return cnt;}
        }
        return cnt;
    }

    /*prepare some product data for print the bill
     * barcode: 'ITEM000000', name: '可口可乐',
       unit: '瓶', category: '食品', subCategory: '碳酸饮料', price: 3.00
    */
    //find the detai of product by the hashmap.keySet(the product on the bill)
    public String[][] Query(Map<String, Integer> m){
        //the product message for test
        String[][] product = {
                {"ITEM000000","可口可乐","瓶","食品","碳酸饮料","3.00"},
                {"ITEM000001","蓝月亮","袋","洗护","洗衣粉","34.00"},
                {"ITEM000002","雪碧","瓶","食品","碳酸饮料","3.00"},
                {"ITEM000003","立白","袋","细化","洗衣粉","50.20"},
                {"ITEM000004","java书籍","本","文化","书籍","49.00"},
                {"ITEM000005","键盘","个","电子产品","配件","300.00"},
        };
        int i = 0;
        String[] ProKey = new String[m.keySet().size()];
        for(Object s :m.keySet()){
            ProKey[i++] = (String)s;
        }
        i =0;
        Map<String, Integer> category = new HashMap<String, Integer>();
        String[][] res = new String[ProKey.length][5];
        for(int j=0;j<ProKey.length;j++){
            for(String[] record: product){
                String[] re = record;
                if(record[0].equals(ProKey[j])){
                    res[j][0] = re[1];//product name
                    res[j][1] = m.get(ProKey[j]).toString();//cnt
                    res[j][2] = re[2];//unit
                    res[j][3] = re[5];//price
                    res[j][4] = re[4];//subcategory
                    Integer cnt = m.get(ProKey[j]);
                    if(category.get(record[4])!= null){
                        cnt = category.get(re[4])+cnt;
                    }
                    category.put(record[4], cnt);
                    break;
                }
            }
        }
        for(String[] sc: res){
            String x = sc[4];
            int is = category.get(x);
            if(is>=10){
                sc[4]="Y";
            }else{
                sc[4]="N";
            }
        }
        return res;
    }

    //count the final bill price and some detail ,print out
    public void print(Map<String, Integer> m){
        String[][] res = Query(m);
        int[] index=new int[res.length];
        int flag = 0;
        double sum=0;
        double save=0;
        System.out.println( "*<没钱赚商店>购物清单* ");
        for(int i = 0;i<res.length;i++){
            double total = Double.parseDouble(res[i][3])*Double.parseDouble(res[i][1]);
            System.out.print("名称："+ res[i][0]+"，数量："+ res[i][1] + res[i][2] + "，单价：" + res[i][3] + "，小计：");
            if(res[i][4].equals("Y")){
                double discount = 0.95;
                System.out.println(total*discount + "(元)，优惠：" + total*(1-discount) + "(元)");
                index[flag++]=i;
                sum+=total*discount;
                save+=total*(1-discount) ;
            }else{
                System.out.println(total + "(元)");
                sum+=total;
            }
        }
        System.out.println("-------------------------------------");
        if(flag>0){
            System.out.print("批发价出售商品：");
            int cnt = flag;
            int i = 0;
            while(cnt>0){
                if(i<=flag){
                    System.out.println("名称：" + res[index[i]][0]+"，数量："+res[index[i]][1]+res[index[i]][2]+" ");
                    i++;
                    cnt--;
                }
            }
            System.out.println("-------------------------------------");
        }
        System.out.print("总计："+sum+"(元) ");
        if(save!=0){
            System.out.print("节省："+ save +"(元)");
        }
        System.out.println();

        // 名称：可口可乐，数量：6瓶，单价：3.00(元)，小计：17.10(元)，优惠：0.90(元)
        //批发价出售商品： 名称：可口可乐，数量：6瓶 名称：雪碧，数量：5瓶
        //总计：47.35(元) 节省：1.65(元)
    }

    //for test
    @Test
    public void test(){
        String [] code = {"ITEM000001", "ITEM000001", "ITEM000001",
                "ITEM000001", "ITEM000001", "ITEM000003-3",
                "ITEM000005", "ITEM000005", "ITEM000005" };
        print(InputBarcode(code));

        String [] code1 = {"ITEM000001", "ITEM000001", "ITEM000001",
                "ITEM000001", "ITEM000001", "ITEM000003-9",
                "ITEM000005", "ITEM000005", "ITEM000005" };
        print(InputBarcode(code1));
    }
}

/*
 * String [] code = {"ITEM000001", "ITEM000001", "ITEM000001",
				"ITEM000001", "ITEM000001", "ITEM000003-2",
				"ITEM000005", "ITEM000005", "ITEM000005" };
 * 'ITEM000001', 'ITEM000001', 'ITEM000001',
 * 'ITEM000001', 'ITEM000001', 'ITEM000003-2',
 * 'ITEM000005', 'ITEM000005', 'ITEM000005'
 */
