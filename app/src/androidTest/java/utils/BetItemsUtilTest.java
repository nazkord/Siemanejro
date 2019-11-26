package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Bet;

public class BetItemsUtilTest {

    BetItemsUtil betItemsUtil = new BetItemsUtil();

    File fl = new File("/Users/nazkord/StudioProjects/SiemanejroProject/app/src/androidTest/java/utils/betsTest");

    FileInputStream fin;
    String result = "";

    {
        try {
            fin = new FileInputStream(fl);
            result = convertStreamToString(fin);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public static void setUp() {
//        Files.readAllBytes()
    }

    @Test
    public void testHashMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Bet> bets = new ArrayList<>();

        try {
            bets = objectMapper.readValue(result, new TypeReference<List<Bet>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        betItemsUtil.createMapItems(bets);
//        String test = "";
//        assertThat().eq
    }

    public String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

}