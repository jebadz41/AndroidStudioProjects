
package com.examples.contacts.data.crud.retrieve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.backendless.Backendless;
import com.examples.contacts.data.crud.R;
import com.examples.contacts.data.crud.common.DataApplication;
import com.examples.contacts.data.crud.common.DefaultCallback;
import com.examples.contacts.data.*;

import java.util.List;

public class ShowByPropertyActivity extends Activity
{
  private TextView titleView;
  private TextView propertyView;
  private ListView entitiesView;
  private Button nextPageButton, previousPageButton;

  private String type;
  private String table;
  private String property;
  private List collection;
  private String[] items;

  private int currentPage;
  private int totalPages;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.show_by_property );

    DataApplication dataApplication = (DataApplication) getApplication();
    table = dataApplication.getChosenTable();

    Backendless.Data.of( RetrieveRecordActivity.entityClass ).getObjectCount( RetrieveRecordActivity.queryBuilder,
      new DefaultCallback<Integer>( ShowByPropertyActivity.this )
      {
        @Override
        public void handleResponse( Integer response )
        {
          collection = RetrieveRecordActivity.getResultCollection();
          currentPage = 1;
          totalPages = (int) Math.ceil( ((double) response) / collection.size() );
          initUI();
          initList();
          initButtons();
          super.handleResponse( response );
        }
      });
  }

  private void initUI()
  {
    titleView = (TextView) findViewById( R.id.showByPropertyTitle );
    propertyView = (TextView) findViewById( R.id.propertyName );
    entitiesView = (ListView) findViewById( R.id.entitiesList );
    previousPageButton = (Button) findViewById( R.id.previousPageButton );
    nextPageButton = (Button) findViewById( R.id.nextPageButton );

    Intent intent = getIntent();
    type = intent.getStringExtra( "type" );
    property = intent.getStringExtra( "property" );

    if( type.equals( "Basic Find" ) )
    {
      titleView.setText( "Basic Find" );
    }
    else if( type.equals( "Find with Sort" ) )
    {
      titleView.setText( "Sorted Find" );
    }
    propertyView.setText( property + ":" );

    previousPageButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        Backendless.Data.of( RetrieveRecordActivity.entityClass ).find( RetrieveRecordActivity.queryBuilder.preparePreviousPage(),
                    new DefaultCallback<List>( ShowByPropertyActivity.this )
        {
          @Override
          public void handleResponse( List response )
          {
            currentPage--;
            collection = response;
            initList();
            initButtons();
            super.handleResponse( response );
          }
        } );
      }
    } );

    nextPageButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        Backendless.Data.of( RetrieveRecordActivity.entityClass ).find( RetrieveRecordActivity.queryBuilder.prepareNextPage(),
                    new DefaultCallback<List>( ShowByPropertyActivity.this )
        {
          @Override
          public void handleResponse( List response )
          {
            currentPage++;
            collection = response;
            initList();
            initButtons();
            super.handleResponse( response );
          }
        } );
      }
    } );
  }

  private void initList()
  {
    items = new String[ collection.size() ];

    if( table.equals( "Contact" ) )
    {
      if( property.equals( "updated" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getUpdated() );
        }
      }
      else if( property.equals( "userEmail" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getUserEmail() );
        }
      }
      else if( property.equals( "ownerId" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getOwnerId() );
        }
      }
      else if( property.equals( "email" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getEmail() );
        }
      }
      else if( property.equals( "number" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getNumber() );
        }
      }
      else if( property.equals( "created" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getCreated() );
        }
      }
      else if( property.equals( "objectId" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getObjectId() );
        }
      }
      else if( property.equals( "name" ) )
      {
        for( int i = 0; i < collection.size(); i++ )
        {
          items[ i ] = String.valueOf( ((Contact) collection.get( i )).getName() );
        }
      }
    }

    ListAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1, items );
    entitiesView.setAdapter( adapter );
  }

  private void initButtons()
  {
    previousPageButton.setEnabled( currentPage != 1 );
    nextPageButton.setEnabled( currentPage != totalPages );
  }
}