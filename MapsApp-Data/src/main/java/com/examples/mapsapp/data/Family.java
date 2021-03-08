
package com.examples.mapsapp.data;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Family
{
  private String objectId;
  private Date updated;
  private String ownerId;
  private String name;
  private Date created;
  private Point location;
  public String getObjectId()
  {
    return objectId;
  }

  public Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public Date getCreated()
  {
    return created;
  }

  public Point getLocation()
  {
    return location;
  }

  public void setLocation( Point location )
  {
    this.location = location;
  }

                                                    
  public Family save()
  {
    return Backendless.Data.of( Family.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Family> callback )
  {
    Backendless.Data.of( Family.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Family.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Family.class ).remove( this, callback );
  }

  public static Family findById( String id )
  {
    return Backendless.Data.of( Family.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Family> callback )
  {
    Backendless.Data.of( Family.class ).findById( id, callback );
  }

  public static Family findFirst()
  {
    return Backendless.Data.of( Family.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Family> callback )
  {
    Backendless.Data.of( Family.class ).findFirst( callback );
  }

  public static Family findLast()
  {
    return Backendless.Data.of( Family.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Family> callback )
  {
    Backendless.Data.of( Family.class ).findLast( callback );
  }

  public static List<Family> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Family.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Family>> callback )
  {
    Backendless.Data.of( Family.class ).find( queryBuilder, callback );
  }
}