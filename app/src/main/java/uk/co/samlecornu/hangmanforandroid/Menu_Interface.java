package uk.co.samlecornu.hangmanforandroid;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Menu_Interface extends Fragment implements View.OnClickListener{

    //VAR AND OBJ's=================================================================================
    private View view;
    private MainActivity activity;
    private Context context;
    private Spinner difficulty_spinner;
    private ArrayAdapter<String> difficulty_spinner_adapter;
    private Button start_game_btn;
    //==============================================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate((R.layout.main_menu), container, false);
        activity = (MainActivity)getActivity();
        context = activity.getApplicationContext();
        difficulty_spinner = view.findViewById(R.id.difficulty_spinner);
        ArrayList<String> difficulty_list = new ArrayList<>();
        difficulty_list.add("Easy");
        difficulty_list.add("Medium");
        difficulty_list.add("Hard");
        difficulty_spinner_adapter = new ArrayAdapter<String>(context, R.layout.spinner_item, difficulty_list);
        difficulty_spinner.setAdapter(difficulty_spinner_adapter);
        difficulty_spinner.setSelection(1);//medium
        start_game_btn = view.findViewById(R.id.start_game_btn);
        start_game_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_game_btn:
                int difficulty = 4;
                String spinner_selection = difficulty_spinner.getSelectedItem().toString().toLowerCase();
                switch (spinner_selection) {
                    case "easy":
                        difficulty = 1;
                        break;
                    case "medium":
                        difficulty = 2;
                        break;
                    case "hard":
                        difficulty = 3;
                        break;
                }
                activity.load_game(difficulty, 0);
                break;
        }
    }
}
