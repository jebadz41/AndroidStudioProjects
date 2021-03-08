
package com.examples.contacts.data.crud.retrieve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.backendless.persistence.DataQueryBuilder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RetrieveRecordActivity extends Activity
{
  private TextView titleView;
  private EditText codeView;
  private Button runCodeButton, sendCodeButton;

  private String code;
  private String table;
  private String type;

  private static List resultCollection;
  private static Object resultObject;
  public static DataQueryBuilder queryBuilder;
  public static Class entityClass;

  private String selectedProperty;
  private String selectedRelatedTable;
  private String selectedRelatedProperty;
  private String relation;
  private String[] relatedProperties;

  public static List getResultCollection()
  {
    return resultCollection;
  }

  public static Object getResultObject()
  {
    return resultObject;
  }

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.sample_code_template );

    DataApplication dataApplication = (DataApplication) getApplication();
    table = dataApplication.getChosenTable();

    type = getIntent().getStringExtra( "type" );

    initUI();
  }

  private void initUI()
  {
    titleView = (TextView) findViewById( R.id.sampleCodeTitle );
    codeView = (EditText) findViewById( R.id.sampleCodeEdit );
    runCodeButton = (Button) findViewById( R.id.runCodeButton );
    sendCodeButton = (Button) findViewById( R.id.sendCodeButton );

    String titleTemplate = getResources().getString( R.string.retrieve_title_template );
    String title = String.format( titleTemplate, table );
    titleView.setText( title );
    if( table.equals( "Contact" ) )
    {
      code = getContactRetrievalCode();
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
      retrieveContactRecord();
    }
  }

  private void onSendCodeButtonClicked()
  {
    Intent nextIntent = new Intent( getBaseContext(), SendEmailActivity.class );
    nextIntent.putExtra( "code", code );
    nextIntent.putExtra( "table", table );
    nextIntent.putExtra( "method", type );
    startActivity( nextIntent );
  }
                                                        
  private String getContactRetrievalCode()
  {
    if( type.equals( "Basic Find" ) )
    {
      return getBasicContactRetrievalCode();
    }
    else if( type.equals( "Find First" ) )
    {
      return getFirstContactRetrievalCode();
    }
    else if( type.equals( "Find Last" ) )
    {
      return getLastContactRetrievalCode();
    }
    else if( type.equals( "Find with Sort" ) )
    {
      return getSortedContactRetrievalCode();
    }
    else if( type.equals( "Find with Relations" ) )
    {
      return getRelatedContactRetrievalCode();
    }
    return null;
  }

  private void retrieveContactRecord()
  {
    if( type.equals( "Basic Find" ) )
    {
      retrieveBasicContactRecord();
    }
    else if( type.equals( "Find First" ) )
    {
      retrieveFirstContactRecord();
    }
    else if( type.equals( "Find Last" ) )
    {
      retrieveLastContactRecord();
    }
    else if( type.equals( "Find with Sort" ) )
    {
      retrieveSortedContactRecord();
    }
    else if( type.equals( "Find with Relations" ) )
    {
      retrieveRelatedContactRecord();
    }
  }

  private String getBasicContactRetrievalCode()
  {
    return "BackendlessDataQuery query = new BackendlessDataQuery();\n"
            + "Contact.findAsync( query, new AsyncCallback<List<Contact>>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( List<Contact> response )\n"
            + "  {\n"
            + "    Contact firstContact = response.get( 0 );\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "} );";
  }

  private void retrieveBasicContactRecord()
  {
    queryBuilder = DataQueryBuilder.create();
    entityClass = Contact.class;
    Contact.findAsync( queryBuilder, new DefaultCallback<List<Contact>>( RetrieveRecordActivity.this )
    {
      @Override
      public void handleResponse( List<Contact> response )
      {
        super.handleResponse( response );

        resultCollection = response;

        AlertDialog.Builder builder = new AlertDialog.Builder( RetrieveRecordActivity.this );
        builder.setTitle( "Property to show:" );
        final String[] properties = { "updated", "userEmail", "ownerId", "email", "number", "created", "objectId", "name" };
        builder.setItems( properties, new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick( DialogInterface dialogInterface, int i )
          {
            Intent nextIntent = new Intent( RetrieveRecordActivity.this, ShowByPropertyActivity.class );
            nextIntent.putExtra( "type", type );
            nextIntent.putExtra( "property", properties[ i ] );
            startActivity( nextIntent );
            dialogInterface.cancel();
          }
        } );
        builder.create().show();
      }
    } );
  }

  private String getFirstContactRetrievalCode()
  {
    return "Contact.findFirstAsync( new AsyncCallback<Contact>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( Contact object )\n"
            + "  {\n"
            + "    //work with the object\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "} );";
  }

  private void retrieveFirstContactRecord()
  {
    Contact.findFirstAsync( new DefaultCallback<Contact>( RetrieveRecordActivity.this )
    {
      @Override
      public void handleResponse( Contact response )
      {
        super.handleResponse( response );
        resultObject = response;
        Intent nextIntent = new Intent( RetrieveRecordActivity.this, ShowEntityActivity.class );
        nextIntent.putExtra( "type", type );
        startActivity( nextIntent );
      }
    } );
  }

  private String getLastContactRetrievalCode()
  {
    return "Contact.findLastAsync( new AsyncCallback<Contact>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( Contact object )\n"
            + "  {\n"
            + "    //work with the object\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "} );";
  }

  private void retrieveLastContactRecord()
  {
    Contact.findLastAsync( new DefaultCallback<Contact>( RetrieveRecordActivity.this )
    {
      @Override
      public void handleResponse( Contact response )
      {
        super.handleResponse( response );
        resultObject = response;
        Intent nextIntent = new Intent( RetrieveRecordActivity.this, ShowEntityActivity.class );
        nextIntent.putExtra( "type", type );
        startActivity( nextIntent );
      }
    } );
  }

  private String getSortedContactRetrievalCode()
  {
    return "QueryOptions queryOptions = new QueryOptions();\n"
            + "List<String> sortByProperties = new ArrayList<String>();\n"
            + "sortByProperties.add( \"someProperty\" );\n"
            + "queryOptions.setSortBy( sortByProperties );\n"
            + "BackendlessDataQuery query = new BackendlessDataQuery(  );\n"
            + "query.setQueryOptions( queryOptions );\n"
            + "Contact.findAsync( query, new AsyncCallback<List<Contact>>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( List<Contact> response )\n"
            + "  {\n"
            + "    Contact firstSortedContact = response.get( 0 );\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "} );";
  }

  private void retrieveSortedContactRecord()
  {
    List<CharSequence> selectedItems = getIntent().getCharSequenceArrayListExtra( "selectedProperties" );
    List<String> sortByProperties = Arrays.asList( selectedItems.toArray( new String[ selectedItems.size() ] ) );
    queryBuilder = DataQueryBuilder.create().setSortBy( sortByProperties );
    entityClass = Contact.class;

    Contact.findAsync( queryBuilder, new DefaultCallback<List<Contact>>( RetrieveRecordActivity.this )
    {
      @Override
      public void handleResponse( List<Contact> response )
      {
        super.handleResponse( response );

        resultCollection = response;

        AlertDialog.Builder builder = new AlertDialog.Builder( RetrieveRecordActivity.this );
        builder.setTitle( "Property to show:" );
        final String[] properties = { "updated", "userEmail", "ownerId", "email", "number", "created", "objectId", "name" };
        builder.setItems( properties, new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick( DialogInterface dialogInterface, int i )
          {
            Intent nextIntent = new Intent( RetrieveRecordActivity.this, ShowByPropertyActivity.class );
            nextIntent.putExtra( "type", type );
            nextIntent.putExtra( "property", properties[ i ] );
            startActivity( nextIntent );
            dialogInterface.cancel();
          }
        } );
        builder.create().show();
      }
    } );
  }

  private String getRelatedContactRetrievalCode()
  {
    return "QueryOptions queryOptions = new QueryOptions();\n"
            + "List<String> relationsToFind = new ArrayList<String>();\n"
            + "queryOptions.setRelated( relationsToFind );\n"
            + "BackendlessDataQuery query = new BackendlessDataQuery();\n"
            + "query.setQueryOptions( queryOptions );\n"
            + "Contact.findAsync( query, new AsyncCallback<List<Contact>>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( List<Contact> collection )\n"
            + "  {\n"
            + "    //work with objects\n"
            + "  }\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "}";
  }

  private void retrieveRelatedContactRecord()
  {
    final List<CharSequence> selectedRelations = getIntent().getCharSequenceArrayListExtra( "selectedRelations" );
    final List<CharSequence> selectedRelatedTables = getIntent().getCharSequenceArrayListExtra( "selectedRelatedTables" );
    final String[] selectedRelationsArray = selectedRelations.toArray( new String[ selectedRelations.size() ] );
    final String[] selectedRelatedTablesArray = selectedRelatedTables.toArray( new String[ selectedRelatedTables.size() ] );

    List<String> relationsToFind = Arrays.asList( selectedRelationsArray );
    queryBuilder = DataQueryBuilder.create().setRelated( relationsToFind );
    entityClass = Contact.class;

    Contact.findAsync( queryBuilder, new DefaultCallback<List<Contact>>( RetrieveRecordActivity.this )
    {
      @Override
      public void handleResponse( List<Contact> response )
      {
        super.handleResponse( response );
        resultCollection = response;

        AlertDialog.Builder builder = new AlertDialog.Builder( RetrieveRecordActivity.this );
        builder.setTitle( "Property to show:" );
        final String[] properties = { "updated", "userEmail", "ownerId", "email", "number", "created", "objectId", "name" };
        builder.setItems( properties, new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick( DialogInterface dialogInterface, int i )
          {
            selectedProperty = properties[ i ];

            AlertDialog.Builder builder = new AlertDialog.Builder( RetrieveRecordActivity.this );
            builder.setTitle( "Related table to show:" );
            builder.setItems( selectedRelatedTablesArray, new DialogInterface.OnClickListener()
            {
              @Override
              public void onClick( DialogInterface dialogInterface, int i )
              {
                selectedRelatedTable = selectedRelatedTablesArray[ i ];
                relation = selectedRelationsArray[ i ];
                if( selectedRelatedTable.equals( "GeoPoint" ) )
                {
                  relatedProperties = new String[] { "Latitude", "Longitude", "Metadata" };
                }
                dialogInterface.cancel();

                AlertDialog.Builder builder = new AlertDialog.Builder( RetrieveRecordActivity.this );
                builder.setTitle( "Related property to show:" );
                if( selectedRelatedTable.equals( "Contact" ) )
                {
                  relatedProperties = new String[] { "updated", "userEmail", "ownerId", "email", "number", "created", "objectId", "name" };
                }

                builder.setItems( relatedProperties, new DialogInterface.OnClickListener()
                {
                  @Override
                  public void onClick( DialogInterface dialogInterface, int i )
                  {
                    selectedRelatedProperty = relatedProperties[ i ];
                    dialogInterface.cancel();
                    Intent nextIntent = new Intent( RetrieveRecordActivity.this, ShowEntityActivity.class );
                    nextIntent.putExtra( "type", type );
                    nextIntent.putExtra( "property", selectedProperty );
                    nextIntent.putExtra( "relation", relation );
                    nextIntent.putExtra( "relatedTable", selectedRelatedTable );
                    nextIntent.putExtra( "relatedProperty", selectedRelatedProperty );
                    startActivity( nextIntent );
                    dialogInterface.cancel();
                  }
                } );
                builder.create().show();
              }
            } );
            builder.create().show();
          }
        } );
        builder.create().show();
      }
    } );
  }
}