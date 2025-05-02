package com.example.notes_app.ui.notes_details;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes_app.R;
import com.example.notes_app.data.model.Note;

import java.util.Date;

public class Notes_Details extends AppCompatActivity {

    private NotesDetailsViewModel notesDetailsViewModel;
    ImageButton addButton;
    LinearLayout fl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_details);

        notesDetailsViewModel = new ViewModelProvider(this).get(NotesDetailsViewModel.class);
        addButton = findViewById(R.id.addBtn);
        fl = findViewById(R.id.LinearLayout);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNoteFragment addNoteDialog = new AddNoteFragment();
                addNoteDialog.show(getSupportFragmentManager(),"Add note");
            }
        });
        notesDetailsViewModel.getNoteResult().observe(this,isSuccess->{
            Log.d("NoteListSize", "Size: " + isSuccess.size());
            if(!isSuccess.isEmpty()){
                Log.d(TAG, "onCreate: ");
                for(int i = 0;i<isSuccess.size();i++){
//                    Log.d(TAG, "onCreate: cardViewiew");
                    CardView cardView = new CardView(this);
                    CardView.LayoutParams cardParams = new CardView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    cardParams.setMargins(16, 16, 16, 16);
                    cardView.setLayoutParams(cardParams);
                    cardView.setRadius(16);
                    cardView.setCardElevation(8);
                    cardView.setCardBackgroundColor(Color.WHITE);
                    LinearLayout ll = new LinearLayout(this);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setPadding(32, 32, 32, 32);
                    ll.setBackgroundColor(Color.WHITE);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    TextView title = new TextView(this);
                    title.setTextColor(Color.parseColor("#6200EE")); // purple_700
                    title.setTextSize(20);
                    title.setTypeface(null, Typeface.BOLD);
//                    title.setGravity(Gravity.START);
                    View divider = new View(this);
                    LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            2
                    );
                    dividerParams.setMargins(0, 16, 0, 16);
                    divider.setLayoutParams(dividerParams);
                    divider.setBackgroundColor(Color.parseColor("#BB86FC")); // purple_200

                    title.setGravity(Gravity.CENTER);
                    title.setText(isSuccess.get(i).getTitle());
                    title.setTextSize(25);
                    TextView content = new TextView(this);
                    content.setGravity(Gravity.CENTER);
                    content.setTextColor(Color.BLACK);
                    content.setTextSize(16);
                    content.setGravity(Gravity.CENTER);
                    content.setText(isSuccess.get(i).getContent());
                    ll.addView(title);
                    ll.addView(divider);
                    ll.addView(content);
                    cardView.addView(ll);
                    fl.addView(cardView);
                }
            }
        });

        notesDetailsViewModel.getAllNotes();

        notesDetailsViewModel.getcreateNoteResult().observe(this, isSuccess ->{

            if(isSuccess!=null){
                CardView cardView = new CardView(this);
                CardView.LayoutParams cardParams = new CardView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                cardParams.setMargins(16, 16, 16, 16);
                cardView.setLayoutParams(cardParams);
                cardView.setRadius(16);
                cardView.setCardElevation(8);
                cardView.setCardBackgroundColor(Color.WHITE);
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setPadding(32, 32, 32, 32);
                ll.setBackgroundColor(Color.WHITE);
                ll.setOrientation(LinearLayout.VERTICAL);
                TextView title = new TextView(this);
                title.setTextColor(Color.parseColor("#6200EE")); // purple_700
                title.setTextSize(20);
                title.setTypeface(null, Typeface.BOLD);
//                    title.setGravity(Gravity.START);
                View divider = new View(this);
                LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        2
                );
                dividerParams.setMargins(0, 16, 0, 16);
                divider.setLayoutParams(dividerParams);
                divider.setBackgroundColor(Color.parseColor("#BB86FC")); // purple_200

                title.setGravity(Gravity.CENTER);
                title.setText(isSuccess.getTitle());
                title.setTextSize(25);
                TextView content = new TextView(this);
                content.setGravity(Gravity.CENTER);
                content.setTextColor(Color.BLACK);
                content.setTextSize(16);
                content.setGravity(Gravity.CENTER);
                content.setText(isSuccess.getContent());
                ll.addView(title);
                ll.addView(divider);
                ll.addView(content);
                cardView.addView(ll);
                fl.addView(cardView);
            }
            else{
                Toast.makeText(Notes_Details.this,"Note with this title already exists",Toast.LENGTH_LONG).show();
            }
        });
    }
}
