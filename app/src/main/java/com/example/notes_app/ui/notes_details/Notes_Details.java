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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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


        //setting addbutton click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNoteFragment addNoteDialog = new AddNoteFragment();
                addNoteDialog.show(getSupportFragmentManager(),"Add note");
            }
        });
        notesDetailsViewModel.getNoteResult().observe(this,isSuccess->{
            fl.removeAllViews(); // 👈 clear existing cards before re-adding updated ones
            //getting all notes
            if(isSuccess!=null && !isSuccess.isEmpty()){
                Log.d(TAG, "onCreate: ");
                for(int i = 0;i<isSuccess.size();i++){
                    //card for each note
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


                    //layout to hold edit and delete buttons
                    LinearLayout horizontalLayout = new LinearLayout(this);
                    horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(horizontalLayout);

                    //adding edit button
                    ImageButton editBtn = new ImageButton(this);
                    editBtn.setImageResource(R.drawable.edit);
                    LinearLayout.LayoutParams layoutParams_edit = new LinearLayout.LayoutParams(150,150);
                    editBtn.setBackground(null);
                    editBtn.setScaleType(ImageView.ScaleType.FIT_CENTER); // 👈 this makes the image fit without cropping
                    editBtn.setAdjustViewBounds(true);
                    editBtn.setLayoutParams(layoutParams_edit);
                    horizontalLayout.addView(editBtn);

                    //setting up click for edit
                    int finalI1 = i;
                    editBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AddNoteFragment editNoteDialog = new AddNoteFragment();
                            Bundle args = new Bundle();
                            args.putString("title", isSuccess.get(finalI1).getTitle());
                            args.putString("content", isSuccess.get(finalI1).getContent());
                            args.putLong("id",isSuccess.get(finalI1).getId());
                            editNoteDialog.setArguments(args);
                            editNoteDialog.show(getSupportFragmentManager(), "Edit note");
                        }

                    });


                    //adding delete button
                    ImageButton deletebtn = new ImageButton(this);
                    deletebtn.setImageResource(R.drawable.delete);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,150);
                    deletebtn.setBackground(null);
                    deletebtn.setScaleType(ImageView.ScaleType.FIT_CENTER); // 👈 this makes the image fit without cropping
                    deletebtn.setAdjustViewBounds(true);
                    deletebtn.setLayoutParams(layoutParams);
                    deletebtn.setForegroundGravity(Gravity.END);

                    //spacer for moving delete button to right
                    View view = new View(this);
                    LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(0,0,1f);
                    horizontalLayout.addView(view,spacerParams);
                    horizontalLayout.addView(deletebtn);


                    int finalI = i;

                    //setting click for delete button
                    deletebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            notesDetailsViewModel.deleteNoteMethod(isSuccess.get(finalI).getTitle());
                            cardView.setVisibility(View.GONE);
                        }
                    });


                    ll.setPadding(32, 32, 32, 32);
                    ll.setBackgroundColor(Color.WHITE);
                    ll.setOrientation(LinearLayout.VERTICAL);

                    //adding title
                    TextView title = new TextView(this);
                    title.setTextColor(Color.parseColor("#6200EE")); // purple_700
                    title.setTextSize(20);
                    title.setTypeface(null, Typeface.BOLD);

                    //divide title and content
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

                    //adding content
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
            //creating new note

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

                //layout to hold edit and delete buttons
                LinearLayout horizontalLayout = new LinearLayout(this);
                horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                ll.addView(horizontalLayout);

                //adding edit button
                ImageButton editBtn = new ImageButton(this);
                editBtn.setImageResource(R.drawable.edit);
                LinearLayout.LayoutParams layoutParams_edit = new LinearLayout.LayoutParams(150,150);
                editBtn.setBackground(null);
                editBtn.setScaleType(ImageView.ScaleType.FIT_CENTER); // 👈 this makes the image fit without cropping
                editBtn.setAdjustViewBounds(true);
                editBtn.setLayoutParams(layoutParams_edit);
                horizontalLayout.addView(editBtn);



                //adding delete button
                ImageButton deletebtn = new ImageButton(this);
                deletebtn.setImageResource(R.drawable.delete);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,150);
                deletebtn.setBackground(null);
                deletebtn.setScaleType(ImageView.ScaleType.FIT_CENTER); // 👈 this makes the image fit without cropping
                deletebtn.setAdjustViewBounds(true);
                deletebtn.setLayoutParams(layoutParams);
                deletebtn.setForegroundGravity(Gravity.END);

                //spacer for moving delete button to right
                View view = new View(this);
                LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(0,0,1f);
                horizontalLayout.addView(view,spacerParams);
                horizontalLayout.addView(deletebtn);




                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notesDetailsViewModel.deleteNoteMethod(isSuccess.getTitle());
                        cardView.setVisibility(View.GONE);
                    }
                });

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

        notesDetailsViewModel.getUpdateNote().observe(this,isSuccess->{
            Toast.makeText(this,"entering",Toast.LENGTH_LONG).show();
            if(isSuccess!=null) {
                fl.removeAllViews(); // 👈 clear existing cards before re-adding updated ones
                notesDetailsViewModel.getAllNotes();
            }

        });



    }
}
