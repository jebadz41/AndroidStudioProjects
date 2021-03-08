
package com.examples.contacts.data.crud.create;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.examples.contacts.data.crud.R;
import com.examples.contacts.data.crud.common.DataApplication;
import com.examples.contacts.data.crud.common.DefaultCallback;
import com.examples.contacts.data.crud.common.SendEmailActivity;
import com.backendless.geo.GeoPoint;
import com.examples.contacts.data.*;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CreateRecordActivity extends Activity
{
  private TextView titleView;
  private EditText codeEdit;
  private Button runCodeButton, sendCodeButton;

  private String code;
  private String table;

                                    
  class CreateContactRecordTask extends AsyncTask<Void, Void, Contact>
  {
    Contact contact = new Contact();

    @Override
    protected void onPreExecute()
    {
      contact.setUserEmail( UUID.randomUUID().toString() );
      contact.setEmail( UUID.randomUUID().toString() );
      contact.setNumber( UUID.randomUUID().toString() );
      contact.setName( UUID.randomUUID().toString() );
    }

    @Override
    protected Contact doInBackground( Void... voids )
    {
      return contact.save();
    }
  };
                                    

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.sample_code_template );

    DataApplication dataApplication = (DataApplication) getApplication();
    table = dataApplication.getChosenTable();

    initUI();
  }

  private void initUI()
  {
    titleView = (TextView) findViewById( R.id.sampleCodeTitle );
    codeEdit = (EditText) findViewById( R.id.sampleCodeEdit );
    runCodeButton = (Button) findViewById( R.id.runCodeButton );
    sendCodeButton = (Button) findViewById( R.id.sendCodeButton );

    String titleTemplate = getResources().getString( R.string.create_record_title_template );
    String title = String.format( titleTemplate, table );
    titleView.setText( title );

    if( table.equals( "Contact" ) )
    {
      code = getContactCreationCode();
    }

    codeEdit.setText( code );

    runCodeButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRunCodeButtonClicked();
      }
    } );

    sendCodeButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onSendCodeButtonClicked();
      }
    } );
  }

  private void onRunCodeButtonClicked()
  {
    if( table.equals( "Contact" ) )
    {
      createContactRecord();
    }
  }

  private void onSendCodeButtonClicked()
  {
    Intent nextIntent = new Intent( getBaseContext(), SendEmailActivity.class );
    nextIntent.putExtra( "code", code );
    nextIntent.putExtra( "table", table );
    nextIntent.putExtra( "method", "Create" );
    startActivity( nextIntent );
  }

  private String getContactCreationCode()
  {
    return "Contact contact = new Contact();\n"
            + "contact.setUserEmail( UUID.randomUUID().toString() );\n"
            + "contact.setEmail( UUID.randomUUID().toString() );\n"
            + "contact.setNumber( UUID.randomUUID().toString() );\n"
            + "contact.setName( UUID.randomUUID().toString() );\n"
            + "contact.saveAsync( new AsyncCallback<Contact>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( Contact savedContact )\n"
            + "  {\n"
            + "    contact = savedContact;\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "});";
  }

  private void createContactRecord()
  {
    Contact contact = null;

    try
    {
      contact = new CreateContactRecordTask().execute().get( 30, TimeUnit.SECONDS );
    }
    catch ( Exception e )
    {
      Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
      return;
    }

    Intent nextIntent = new Intent( CreateRecordActivity.this, CreateSuccessActivity.class );
    startActivity( nextIntent );
  }
}