package uk.co.samlecornu.hangmanforandroid;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Game_Interface extends Fragment implements View.OnClickListener{

    //VAR AND OBJ's=================================================================================
    private View view;
    private MainActivity activity;
    private String word;
    private int difficulty;
    private ArrayList<Character> word_progress;
    private TextView word_view;
    private int score;
    private TextView score_view;
    private int incorrect_guesses;
    private ImageView hangman_view;
    private Button a_btn;   private Button n_btn;
    private Button b_btn;   private Button o_btn;
    private Button c_btn;   private Button p_btn;
    private Button d_btn;   private Button q_btn;
    private Button e_btn;   private Button r_btn;
    private Button f_btn;   private Button s_btn;
    private Button g_btn;   private Button t_btn;
    private Button h_btn;   private Button u_btn;
    private Button i_btn;   private Button v_btn;
    private Button j_btn;   private Button w_btn;
    private Button k_btn;   private Button x_btn;
    private Button l_btn;   private Button y_btn;
    private Button m_btn;   private Button z_btn;
    private ArrayList<Button> keyboardInput;
    //==============================================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate((R.layout.game_interface), container, false);
        activity = (MainActivity)getActivity();

        //Initialise score
        score_view = view.findViewById(R.id.score_view);
        score = 0;

        Bundle bundle = this.getArguments();
        if(bundle != null){
             word = bundle.getString("word", "word");
             difficulty = bundle.getInt("difficulty", 4);
             addToScore(bundle.getInt("score", 0));
        }else{
            /** go back and report error*/
        }
        //INITIALISE BUTTONS AND CLICK EVENTS
        keyboardInput = new ArrayList<>();
        a_btn = view.findViewById(R.id.a_btn);    n_btn = view.findViewById(R.id.n_btn);
        keyboardInput.add(a_btn);                 keyboardInput.add(n_btn);
        b_btn = view.findViewById(R.id.b_btn);    o_btn = view.findViewById(R.id.o_btn);
        keyboardInput.add(b_btn);                 keyboardInput.add(o_btn);
        c_btn = view.findViewById(R.id.c_btn);    p_btn = view.findViewById(R.id.p_btn);
        keyboardInput.add(c_btn);                 keyboardInput.add(p_btn);
        d_btn = view.findViewById(R.id.d_btn);    q_btn = view.findViewById(R.id.q_btn);
        keyboardInput.add(d_btn);                 keyboardInput.add(q_btn);
        e_btn = view.findViewById(R.id.e_btn);    r_btn = view.findViewById(R.id.r_btn);
        keyboardInput.add(e_btn);                 keyboardInput.add(r_btn);
        f_btn = view.findViewById(R.id.f_btn);    s_btn = view.findViewById(R.id.s_btn);
        keyboardInput.add(f_btn);                 keyboardInput.add(s_btn);
        g_btn = view.findViewById(R.id.g_btn);    t_btn = view.findViewById(R.id.t_btn);
        keyboardInput.add(g_btn);                 keyboardInput.add(t_btn);
        h_btn = view.findViewById(R.id.h_btn);    u_btn = view.findViewById(R.id.u_btn);
        keyboardInput.add(h_btn);                 keyboardInput.add(u_btn);
        i_btn = view.findViewById(R.id.i_btn);    w_btn = view.findViewById(R.id.w_btn);
        keyboardInput.add(i_btn);                 keyboardInput.add(w_btn);
        j_btn = view.findViewById(R.id.j_btn);        v_btn = view.findViewById(R.id.v_btn);
        keyboardInput.add(j_btn);                 keyboardInput.add(v_btn);
        k_btn = view.findViewById(R.id.k_btn);    x_btn = view.findViewById(R.id.x_btn);
        keyboardInput.add(k_btn);                 keyboardInput.add(x_btn);
        l_btn = view.findViewById(R.id.l_btn);    y_btn = view.findViewById(R.id.y_btn);
        keyboardInput.add(l_btn);                 keyboardInput.add(y_btn);
        m_btn = view.findViewById(R.id.m_btn);    z_btn = view.findViewById(R.id.z_btn);
        keyboardInput.add(m_btn);                 keyboardInput.add(z_btn);
        for(Button btn: keyboardInput){
            btn.setOnClickListener(this);
        }

        //initialise incorrect guesses
        hangman_view = view.findViewById(R.id.hangman_view);
        incorrect_guesses = 0;
        updateHangman();

        //DISPLAY WORD
        word_view = view.findViewById(R.id.word_view);
        word_progress = new ArrayList<>();
        startGame();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Button clicked_btn = view.findViewById(id);
        makeGuess(clicked_btn.getText().toString().charAt(0));
        clicked_btn.setVisibility(View.INVISIBLE);
    }

    /**
     * this sets up the game interface for a new session
     */
    private void startGame() {
        word_progress.clear();
        for(int i = 0; i < word.length(); i++){
            word_progress.add('-');
        }
        updateWord();
    }

    /**
     * This handles the logic for when a guess is made
     * This includes the word hangman and score displayed
     * @param c
     */
    private void makeGuess(Character c) {
        System.out.println(c);
        Boolean correct = false;
        for(int i = 0; i < word.length(); i++){
            System.out.println(word.charAt(i)+"-"+c);
            if(Character.toLowerCase(word.charAt(i)) == Character.toLowerCase(c)){
                correct = true;
                word_progress.set(i,word.charAt(i));
                addToScore(50);
            }
        }
        if(correct){
            //good guess
            updateWord();
        }else{
            //bad guess
            incorrect_guesses++;
            updateHangman();
        }
    }

    /**
     * This methods update the score and the score view for the player
     * the int passed will be added to the current score
     * @param score
     */
    public void addToScore(int score) {
        this.score += score;
        score_view.setText(String.valueOf(this.score));
    }

    /**
     * This will update word view according to the word progress
     */
    private void updateWord() {
        String w = "";
        for(Character c: word_progress){
            w += c;
        }
        word_view.setText(w);
        if(!w.contains("-")){
            gameOver(true);
        }
    }

    /**
     * This method will update the hangman image
     * calculated by using the amount of incorrect guesses
     * if there are 7 or more bad guesses gameOver(false) is called
     */
    public void updateHangman() {
        int imageId = view.getResources().getIdentifier("hangman_" + incorrect_guesses  , "drawable", activity.getPackageName());
        Drawable hangman_image = view.getResources().getDrawable(imageId );
        hangman_view.setImageDrawable(hangman_image);
        if(incorrect_guesses >= 7){
            gameOver(false);
        }
    }

    /**
     * When the game is over by win or lose condition this method is called
     * pass true if they have won the game, pass false if they have lost
     * A relevant message will be displayed and handle the next step
     * @param won
     */
    private void gameOver(Boolean won) {
        String title, message, button_text;
        final int new_score;
        if(won){
            new_score = score;
            title = "You have Won!";
            message = "The word was "+word+", and you're current score is "+score+".";
            button_text = "Go to the next round.";
        }else{
            new_score = 0;
            title = "You have Lost.";
            message = "The word was "+word+", please try again.";
            button_text = "Restart game.";
        }
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);

        // add a button
        builder.setPositiveButton(button_text,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.load_game(difficulty, new_score);
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
