package com.example.roomapp.ui.activities.addnoteactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomapp.R;
import com.example.roomapp.local.AppDatabase;
import com.example.roomapp.local.dao.NotesDao;
import com.example.roomapp.model.entity.Note;

import java.util.UUID;

public class AddNoteActivity  extends AppCompatActivity {


    private EditText heading;
    private EditText context;
    private Button saveButton;

    private NotesDao notesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        configureUI();
        configureDatabase();
        setListeners();

    }


    private void configureUI(){
        heading = findViewById(R.id.heading);
        context = findViewById(R.id.context);
        saveButton = findViewById(R.id.save_button);
    }

    private  void setListeners(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast. makeText(getApplicationContext(), "Note saved successfully", Toast. LENGTH_SHORT).show();
                insertingNote();

            }
        });

    }


    private void insertingNote(){

        String idOfNote = UUID.randomUUID().toString();
        String headerOfNote = heading.getText().toString();
        String contextOfNote = context.getText().toString();

        Note newNote = new Note(idOfNote, headerOfNote, contextOfNote);


        notesDao.insert(newNote);
        finish();
    }

    private void configureDatabase() {
        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        this.notesDao = appDatabase.getNotesDao();
    }
}
