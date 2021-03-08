
package com.examples.contacts.data.crud.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.backendless.Backendless;
import com.examples.contacts.data.*;
import com.examples.contacts.data.crud.R;
import com.examples.contacts.data.crud.common.DataApplication;
import com.examples.contacts.data.crud.common.Defaults;

public class SelectTableActivity extends Activity
{
  private ListView tablesListView;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.select_table );

    Backendless.setUrl( Defaults.SERVER_URL );
    Backendless.initApp( this, Defaults.APPLICATION_ID, Defaults.API_KEY );
    Backendless.Data.mapTableToClass( "Contact", Contact.class );
    
    initUI();
  }

  private void initUI()
  {
    tablesListView = (ListView) findViewById( R.id.tablesList );

    String[] tables = new String[] { "Contact" };

    ArrayAdapter adapter = new ArrayAdapter<String>( this, R.layout.list_item_with_arrow, R.id.itemName, tables );
    tablesListView.setAdapter( adapter );

    tablesListView.setOnItemClickListener( new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick( AdapterView<?> adapterView, View view, int i, long l )
      {
        TextView tableNameView = (TextView) view.findViewById( R.id.itemName );
        DataApplication dataApplication = (DataApplication) getApplication();
        dataApplication.setChosenTable( tableNameView.getText().toString() );

        startActivity( new Intent( SelectTableActivity.this, SelectTableOperationActivity.class ) );
      }
    } );
  }
}
                