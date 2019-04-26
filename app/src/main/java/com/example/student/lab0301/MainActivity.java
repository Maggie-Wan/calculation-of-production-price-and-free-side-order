package com.example.student.lab0301;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView quantity;
    TextView price;
    CheckBox veg;

    //一般命名變數時，前面加個m代表整個類別可以使用的變數，設定初始值
    private int mQuantity=0;
    private int mPrice=50;
    //可給初始容量，如果超過會自動擴大，如果不給會用預設，大約16個字元
    private StringBuilder msg=new StringBuilder(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity=(TextView)findViewById(R.id.quantity);
        price=(TextView)findViewById(R.id.price);
        veg=(CheckBox)findViewById(R.id.veg);
    }

    //送出
    public void send(View v){
        displaytotalPirce();
    }

    //顯示數量
    public void displayquantity(){
        String ntoString=String.valueOf(mQuantity);
        quantity.setText(ntoString);
    }

    //加數量
    public void add(View v){
        mQuantity++;
        displayquantity();
        price.setText("");
    }

    //減數量
    public void subtract(View v){
        if(mQuantity>0){
            mQuantity--;
            displayquantity();
        }
        price.setText("");

    }

    //計算總價
    public void displaytotalPirce(){
        int totalP=mQuantity*mPrice;

        //因為字串是字元的陣列，所以可以用.length()取得字串長度
        int len=msg.length();

        //如果沒有把原本的字串刪除，會連上一次顯示的字串一起出現
        msg.delete(0,len);

        //貨幣格式化=>可以出現$符號
        String pay= NumberFormat.getCurrencyInstance().format(totalP);

        if(mQuantity==0){
            if(veg.isChecked()) {
                msg.append("客戶:鳴人\n商品：臭豆腐\n是否要加泡菜?是\n免費試吃");
            }else{
                msg.append("客戶:鳴人\n商品：臭豆腐\n是否要加泡菜?否\n免費試吃");
            }
        }else{
            if(veg.isChecked()){
                msg.append("客戶:鳴人\n商品：臭豆腐\n是否要加泡菜?是\n")
                        .append("數量:").append(mQuantity)
                        .append("\n總額:").append(pay)
                        .append("\n謝謝!");
            }else{
                msg.append("客戶:鳴人\n商品：臭豆腐\n是否要加泡菜?否\n")
                        .append("數量:").append(mQuantity)
                        .append("\n總額:").append(pay)
                        .append("\n謝謝!");
            }
        }

        price.setText(msg);
    }
        //如果要把測試程式關閉，xml中的checkbox的onclick也必須移除，不然會找不到出現閃退問題
        public void chk(View v){
            if(veg.isChecked()){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }

        //統一管理所有button
        public void myClick(View v){
            //因為每個button(veiw)元件都共用一個myClick()方法，所以要先取得要用的v(這邊是指正在使用的那個button)的id
            //因為id是放在R檔中，裡面都是int
            int id=v.getId();
            switch(id){
                case R.id.veg:
                    chk(v);
                    break;
                case R.id.add:
                    add(v);
                    break;
                case R.id.subtract:
                    subtract(v);
                    break;
                case R.id.send:
                    send(v);
                    break;
            }

        }
}

