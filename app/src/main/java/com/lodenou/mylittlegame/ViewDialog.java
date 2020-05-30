package com.lodenou.mylittlegame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ViewDialog {

    StartActivity mStartActivity;
    private String[] mClasses = {"Guerrier","Magicien","Moine", "Ninja", "Samouraï","Paysan"};
    public static final String EXTRA_CHARACTER_NAME = "MY_EPIC_KEY";
    private MusicService mServ;


    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_box);
        final EditText dialogEditText = dialog.findViewById(R.id.edit_text_dialog);

        // Spinner ---------------------------------------------------------------------------------
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter( activity, R.layout.item_spinner, mClasses);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setBackgroundColor(127);
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                aa,
                R.layout.contact_spinner_row_nothing_selected,
                activity));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //------------------------------------------------------------------------------------------
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_cancel);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button dialogButton2 = (Button) dialog.findViewById(R.id.btn_done);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogEditText.getText().toString().trim().length() == 0 || spinner.getItemAtPosition(spinner.getSelectedItemPosition()) == null){
                    Toast.makeText(dialog.getContext(),
                            "Fill in all fields",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(dialog.getContext(), GameActivity.class);
                    String characterName = dialogEditText.getText().toString();
                    intent.putExtra(EXTRA_CHARACTER_NAME, characterName);
                    activity.startActivity(intent);
                    dialog.dismiss();


                }
            }
        });



                dialog.show();
    }


    public void getDialoginfos() {
        ;
    }

}
