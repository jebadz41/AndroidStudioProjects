
package com.examples.contacts.data;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Contact
{
  private Date updated;
  private String userEmail;
  private String ownerId;
  private String email;
  private String number;
  private Date created;
  private String objectId;
  private String name;
  public Date getUpdated()
  {
    return updated;
  }

  public String getUserEmail()
  {
    return userEmail;
  }

  public void setUserEmail( String userEmail )
  {
    this.userEmail = userEmail;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public String getNumber()
  {
    return number;
  }

  public void setNumber( String number )
  {
    this.number = number;
  }

  public Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

                                                    
  public Contact save()
  {
    return Backendless.Data.of( Contact.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Contact> callback )
  {
    Backendless.Data.of( Contact.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Contact.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Contact.class ).remove( this, callback );
  }

  public static Contact findById( String id )
  {
    return Backendless.Data.of( Contact.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Contact> callback )
  {
    Backendless.Data.of( Contact.class ).findById( id, callback );
  }

  public static Contact findFirst()
  {
    return Backendless.Data.of( Contact.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Contact> callback )
  {
    Backendless.Data.of( Contact.class ).findFirst( callback );
  }

  public static Contact findLast()
  {
    return Backendless.Data.of( Contact.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Contact> callback )
  {
    Backendless.Data.of( Contact.class ).findLast( callback );
  }

  public static List<Contact> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Contact.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Contact>> callback )
  {
    Backendless.Data.of( Contact.class ).find( queryBuilder, callback );
  }
}