
package com.examples.contacts.data.crud.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.messaging.MessageStatus;
import com.examples.contacts.data.crud.R;

public class SendEmailActivity extends Activity
{
  private EditText emailEdit;
  private Button sendButton;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.send_email );

    initUI();
  }

  private void initUI()
  {
    emailEdit = (EditText) findViewById( R.id.emailAddressEdit );
    sendButton = (Button) findViewById( R.id.sendButton );

    sendButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onSendButtonClicked();
      }
    } );
  }

  private void onSendButtonClicked()
  {
    String email = emailEdit.getText().toString();
    String code = getIntent().getStringExtra( "code" );
    String method = getIntent().getStringExtra( "method" );
    String table = getIntent().getStringExtra( "table" );

    String subject = String.format( "Code generation sample for: %s in %s table", method, table );

    Backendless.Messaging.sendTextEmail( subject, code, email, new DefaultCallback<MessageStatus>( SendEmailActivity.this )
    {
      @Override
      public void handleResponse( MessageStatus response )
      {
        super.handleResponse( response );
        Toast.makeText( getBaseContext(), "Email sent.", Toast.LENGTH_SHORT ).show();
      }
    } );
  }
}
                