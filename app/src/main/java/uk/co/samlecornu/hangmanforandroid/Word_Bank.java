package uk.co.samlecornu.hangmanforandroid;
import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Word_Bank {
    private Context context;
    private ArrayList<String> all_words = new ArrayList<>();
    private ArrayList<String> small_words = new ArrayList<>();
    private ArrayList<String> medium_words = new ArrayList<>();
    private ArrayList<String> large_words = new ArrayList<>();

    public Word_Bank(Context _context){
        context = _context;
        loadWords();
    }

    private void loadWords() {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.dictionary);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                all_words.add(line);
                if(line.length() < 6){//short word
                    small_words.add(line);
                }else if(line.length() >= 6 && line.length() < 8){//medium word
                    medium_words.add(line);
                }else{
                    large_words.add(line);//must be a long word
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GETTERS & SETTERS=============================================================================
    public String getRandomWord(){
        return all_words.get(new Random().nextInt(all_words.size()));
    }
    public String getSmallWord(){
        return small_words.get(new Random().nextInt(small_words.size()));
    }public String getMediumWord(){
        return medium_words.get(new Random().nextInt(medium_words.size()));
    }
    public String getLargeWord(){
        return large_words.get(new Random().nextInt(large_words.size()));
    }
    //==============================================================================================
}
