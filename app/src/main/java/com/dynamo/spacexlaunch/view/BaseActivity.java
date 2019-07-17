package com.dynamo.spacexlaunch.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.dynamo.spacexlaunch.R;
import com.dynamo.spacexlaunch.Util.AppConstants;
import com.dynamo.spacexlaunch.model.resource.ErrorDetail;
import com.dynamo.spacexlaunch.view.fragment.LoaderDialogFragment;

/**
 * Created by dhandk2 on 17,July,2019
 * Manages progress and alert dialog .
 * All activity need to extend this class
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Toolbar toolBar;
    private LoaderDialogFragment loaderDialogFragment;
    private AlertDialog alertDialog;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        context = this;

        toolBar = (Toolbar) this.findViewById(R.id.toolbar);

        if (toolBar != null) {
            setSupportActionBar(toolBar);
            if (getSupportActionBar() != null) {
                setDisplayHomeEnabled(true);
            }
        }
    }

    protected abstract int getLayoutResource();

    public void setDisplayHomeEnabled(boolean b) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        toolBar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        toolBar.setTitle(titleId);
    }

    public Toolbar getToolBar() {
        return toolBar;
    }

    /*
      This method displays progress dialog
     */
    public void showProgressDialog(String messageToShow) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        loaderDialogFragment = new LoaderDialogFragment();
        loaderDialogFragment.setCancelable(false);
        loaderDialogFragment.show(fragmentManager, AppConstants.FRAGMENT_TAG);

    }
    /*
      This method hides progress dialog
        */
    public void hideProgressDialog() {

        if (loaderDialogFragment != null) {
            loaderDialogFragment.dismiss();
            loaderDialogFragment = null;
        }
    }

    public void dismissAlertDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }

        alertDialog = null;
    }

    /*
       Displays Alert Dialog for Error Messages
     */
    public void showErrorDialog(final ErrorDetail errorDetail) {

        dismissAlertDialog();

        String title ;

        if(errorDetail.getErrorCode() > 0){
            title = AppConstants.Error.API_ERROR_TITLE;
        }else
            title = AppConstants.Error.CONNECTIVTY_TITLE;

        String buttonText = getString(R.string.okay);


        alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(errorDetail.getErrorDetail());

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alertDialog.dismiss();
            }
        });

        // Showing Alert Message
      alertDialog.show();

    }
}

