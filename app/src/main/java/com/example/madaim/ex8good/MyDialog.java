package com.example.madaim.ex8good;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Madaim on 23/02/2018.
 */

public class MyDialog extends DialogFragment {
    public final static int EXIT_DIALOG = 1;
    public final static int PRECISION_DIALOG = 2;
    private int reqCode;
    private ResultListener listener;
    private static MyDialog dlg = null;

    public static MyDialog newInstance(int requestCode) {
        if (dlg== null)
            dlg = new MyDialog();
        Bundle args = new Bundle();
        args.putInt("rc", requestCode);
        dlg.setArguments(args);
        return dlg;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.reqCode = getArguments().getInt("rc");
        if (reqCode == EXIT_DIALOG)
            return buildExitDialog().create();
        else
            return null;
    }

    private AlertDialog.Builder buildExitDialog() {
        return new AlertDialog.Builder(getActivity()).setTitle("Closing the application")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (listener!=null)
                            listener.onFinishedDialog(reqCode,"ok");
                     }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();

                            }
                        }
                );

    }

    public interface ResultListener {
        void onFinishedDialog(int requestCode, Object results);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (ResultListener) context;
        } catch (ClassCastException e) {
            //Toast.makeText(context, "hosting activity must implements ResultListener", Toast.LENGTH_LONG).show();
            throw new ClassCastException("hosting activity must implements ResultListener");
        }
    }
}