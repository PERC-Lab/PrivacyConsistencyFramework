package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolicyCheck extends AppCompatActivity {

    private ListView policyList;
    String term = "usage";
    ArrayList<String> listOfSentence = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_check);
        getSupportActionBar().setTitle("Policy Check");


        List<List<String>> docu = getList();
        TFIDFCalculator calculator = new TFIDFCalculator();
        double tfidfValue = calculator.tfIdf(docu.get(0), docu, term);

        listOfSentence.add(term + ": "+tfidfValue);

        policyList = findViewById(R.id.listOFPolicy);
        for(List<String> a : docu){
            if(!a.toString().equals("[]")){
                listOfSentence.add(a.toString());
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfSentence);
        policyList.setAdapter(arrayAdapter);

    }

    public  List<List<String>> getList() {
        String text = "Usage and Log Information. We collect service-related, diagnostic, and performance information. This includes information about your activity (such as how you use our Services, how you interact with others using our Services, and the like), log files, and diagnostic, crash, website, and performance logs and reports.\r\n" +
                "Transactional Information. If you pay for our Services, we may receive information and confirmations, such as payment receipts, including from app stores or other third parties processing your payment.\r\n" +
                "Device and Connection Information. We collect device-specific information when you install, access, or use our Services. This includes information such as hardware model, operating system information, browser information, IP address, mobile network information including phone number, and device identifiers. We collect device location information if you use our location features, such as when you choose to share your location with your contacts, view locations nearby or those others have shared with you, and the like, and for diagnostics and troubleshooting purposes such as if you are having trouble with our app�s location features.\r\n" +
                "Cookies. We use cookies to operate and provide our Services, including to provide our Services that are web-based, improve your experiences, understand how our Services are being used, and customize our Services. For example, we use cookies to provide WhatsApp for web and desktop and other web-based services. We may also use cookies to understand which of our FAQs are most popular and to show you relevant content related to our Services. Additionally, we may use cookies to remember your choices, such as your language preferences, and otherwise to customize our Services for you. Learn more about how we use cookies to provide you our Services.\r\n" +
                "Status Information. We collect information about your online and status message changes on our Services, such as whether you are online (your �online status�), when you last used our Services (your �last seen status�), and when you last updated your status message.\r\n" +
                "\r\n" +
                "\r\n" +
                "Third-Party Information\r\n" +
                "Information Others Provide About You. We receive information other people provide us, which may include information about you. For example, when other users you know use our Services, they may provide your phone number from their mobile address book (just as you may provide theirs), or they may send you a message, send messages to groups to which you belong, or call you.\r\n" +
                "Third-Party Providers. We work with third-party providers to help us operate, provide, improve, understand, customize, support, and market our Services. For example, we work with companies to distribute our apps, provide our infrastructure, delivery, and other systems, supply map and places information, process payments, help us understand how people use our Services, and market our Services. These providers may provide us information about you in certain circumstances; for example, app stores may provide us reports to help us diagnose and fix service issues.\r\n" +
                "Third-Party Services. We allow you to use our Services in connection with third-party services. If you use our Services with such third-party services, we may receive information about you from them; for example, if you use the WhatsApp share button on a news service to share a news article with your WhatsApp contacts, groups, or broadcast lists on our Services, or if you choose to access our Services through a mobile carrier�s or device provider�s promotion of our Services. Please note that when you use third-party services, their own terms and privacy policies will govern your use of those services.\r\n" +
                "How We Use Information\r\n" +
                "We use all the information we have to help us operate, provide, improve, understand, customize, support, and market our Services.\r\n" +
                "\r\n" +
                "\r\n" +
                "Our Services. We operate and provide our Services, including providing customer support, and improving, fixing, and customizing our Services. We understand how people use our Services, and analyze and use the information we have to evaluate and improve our Services, research, develop, and test new services and features, and conduct troubleshooting activities. We also use your information to respond to you when you contact us. We use cookies to operate, provide, improve, understand, and customize our Services.\r\n" +
                "Safety and Security.";
        String[] paragraphs = text.split("(?m)(?=^\\s{4})");
        List<List<String>> docu = new ArrayList<>();

        for (String paragraph : paragraphs) {
            String temp = paragraph.trim();
            String temp2 = temp.replaceAll("[^\\w\\s]", "");
            String [] temp3 = temp2.split(" ");
            List<String> oneParagraph = Arrays.asList(temp3);
            docu.add(oneParagraph);
        }
        return docu;
    }
}
