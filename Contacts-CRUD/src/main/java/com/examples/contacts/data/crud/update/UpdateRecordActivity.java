
package com.examples.contacts.data.crud.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.examples.contacts.data.crud.R;
import com.examples.contacts.data.crud.common.DataApplication;
import com.examples.contacts.data.crud.common.DefaultCallback;
import com.examples.contacts.data.crud.common.SendEmailActivity;
import com.examples.contacts.data.*;
import com.backendless.geo.GeoPoint;

import java.util.Collections;

import java.util.Random;
import java.util.UUID;

public class UpdateRecordActivity extends Activity
{
  private TextView titleView;
  private EditText codeView;
  private Button runCodeButton, sendCodeButton;

  private String code;
  private String table;

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
    codeView = (EditText) findViewById( R.id.sampleCodeEdit );
    runCodeButton = (Button) findViewById( R.id.runCodeButton );
    sendCodeButton = (Button) findViewById( R.id.sendCodeButton );

    String titleTemplate = getResources().getString( R.string.update_title_template );
    String title = String.format( titleTemplate, table );

    titleView.setText( title );

    if( table.equals( "Contact" ) )
    {
      code = getContactUpdateCode();
    }

    codeView.setText( code );

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
      updateContactRecord();
    }
  }

  private void onSendCodeButtonClicked()
  {
    Intent nextIntent = new Intent( getBaseContext(), SendEmailActivity.class );
    nextIntent.putExtra( "code", code );
    nextIntent.putExtra( "table", table );
    nextIntent.putExtra( "method", "Update" );
    startActivity( nextIntent );
  }

  private String getContactUpdateCode()
  {
    return "public void update( Contact contact )\n"
            + "{\n"
            + "  contact.setUserEmail( UUID.randomUUID().toString() );\n"
            + "  contact.setEmail( UUID.randomUUID().toString() );\n"
            + "  contact.setNumber( UUID.randomUUID().toString() );\n"
            + "  contact.setName( UUID.randomUUID().toString() );\n"
            + "  contact.saveAsync( new AsyncCallback<Contact>()\n"
            + "  {\n"
            + "    @Override\n"
            + "    public void handleResponse( Contact updatedRecord )\n"
            + "    {\n"
            + "      //work with object\n"
            + "    }\n"
            + "    @Override\n"
            + "    public void handleFault( BackendlessFault fault )\n"
            + "    {\n"
            + "      Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "    }\n"
            + "  }\n"
            + "}";
  }

  private void updateContactRecord()
  {
    Contact.findFirstAsync( new DefaultCallback<Contact>( UpdateRecordActivity.this )
    {
      @Override
      public void handleResponse( Contact response )
      {
        super.handleResponse( response );
        Contact contact = response;
        contact.setUserEmail( UUID.randomUUID().toString() );
        contact.setEmail( UUID.randomUUID().toString() );
        contact.setNumber( UUID.randomUUID().toString() );
        contact.setName( UUID.randomUUID().toString() );

        contact.saveAsync( new DefaultCallback<Contact>( UpdateRecordActivity.this )
        {
          @Override
          public void handleResponse( Contact response )
          {
            super.handleResponse( response );
            Intent nextIntent = new Intent( UpdateRecordActivity.this, UpdateSuccessActivity.class );
            startActivity( nextIntent );
          }
        } );
      }
    } );
  }
}