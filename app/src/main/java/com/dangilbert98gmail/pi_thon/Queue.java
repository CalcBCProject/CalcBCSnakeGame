package com.dangilbert98gmail.pi_thon;

/**
 * Created by Samsung on 5/15/2016.
 */
public class Queue
{

	private int size;
	private Node head;
	private Node tail;
	/**
	 * Empty LinkedList
	 */
	public Queue()
	{
		size = 0;
		head = null;
		tail = null;
	}
	/**
	 * @return size
	 */
	public int size()
	{
		return size;
	}
	/**
	 * @param o object to be added
	 * @return true
	 */
	public boolean add( Object o )
	{
		add( size, o );
		return true;
	}
	/**
	 * @param slot slot to be added to
	 * @param o object to be added
	 */
	public void add( int slot, Object o )
	{
		Node n;
		Node current = head;
		if ( slot > size || slot < 0 )
		{
			return;
		}
		else
		{
			if ( size == 0 )
			{
				n = new Node( o );
				head = n;
				tail = n;
			}
			else if ( slot == 0 )
			{
				n = new Node( o, head );
				head = n;
			}
			else
			{
				for ( int i = 0; i < slot - 1; i++ )
				{
					current = current.getNext();
				}
				n = new Node( o, current.getNext() );
				current.setNext( n );
				if ( size == slot )
				{
					tail = n;
				}
			}
			size++;
		}
	}
	/**
	 * @param slot what slot is to be removed from
	 * @return object that is removed
	 */
	public Object remove( int slot )
	{
		Node beforeSlot = head;
		Boolean last = ( slot == size - 1 );
		Boolean first = ( slot == 0 );
		Node temp = null;
		if ( slot >= size || slot < 0 )
		{
			return null;
		}
		else
		{
			for ( int i = 0; i < slot - 1; i++ )
			{
				beforeSlot = beforeSlot.getNext();
			}
			if ( last )
			{
				temp = tail;
				tail = beforeSlot;
			}
			if ( first )
			{
				temp = head;
				head = head.getNext();
			}
			if ( !first && !last )
			{
				temp = beforeSlot.getNext();
				beforeSlot.setNext( beforeSlot.getNext().getNext() );
			}
			size--;
			return temp.getData();
		}
	}
	/**
	 * @param o object to be searched for and removed
	 * @return true if it was found in the LinkedList
	 */
	public boolean remove( Object o )
	{
		int spot = indexOf( o );
		if ( spot >= 0 )
		{
			remove( spot );
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * @param slot slot where data will be acquired from
	 * @return object found
	 */
	public Object get( int slot )
	{
		Node temp = head;
		if ( slot >= size || slot < 0 )
		{
			return null;
		}
		else
		{
			for ( int i = 0; i < slot; i++ )
			{
				temp = temp.getNext();
			}
			return temp.getData();
		}
	}
	/**
	 * @param slot slot to set data at
	 * @param o object to set data to
	 * @return the previous data
	 */
	public Object set( int slot, Object o)
	{
		if ( slot < 0 || slot >= size )
		{
			return null;
		}
		Object ret;
		Node n = head;
		for ( int i = 0; i < slot; i++ )
		{
			n = n.getNext();
		}
		ret = n.getData();
		n.setData( o );
		return ret;
	}
	/**
	 * @param o object to be searched for
	 * @return slot where it is found, -1 if not found
	 */
	public int indexOf( Object o )
	{
		Node cur = head;
		if ( size != 0 )
		{
			for ( int i = 0; i < size; i++ )
			{
				if ( cur.getData().equals(o) )
				{
					return i;
				}
				cur = cur.getNext();
			}
		}
		return -1;
	}
	/**
	 * @param o object to be searched for
	 * @return true if found, false if not
	 */
	public boolean contains( Object o )
	{
		return indexOf( o ) >= 0;
	}
	/**
	 * clears all data
	 */
	public void clear()
	{
		size = 0;
	}
	/**
	 * @return true if empty, false if not
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	/**
	 * @param o object to be searched for
	 * @return slot number of last instance of the data, -1 if not found
	 */
	public int lastIndexOf( Object o )
	{
		Node cur = head;
		int last = -1;
		if ( size != 0 )
		{
			for ( int i = 0; i < size; i++ )
			{
				if ( cur.getData().equals(o) )
				{
					last = i;
				}
				cur = cur.getNext();
			}
			return last;
		}
		return -1;
	}
	/**
	 * @return an array containing the data from the linked list
	 */
	public Object [] toArray()
	{
		if ( size == 0 )
		{
			return null;
		}
		Node cur = head;
		Object [] temp = new Object [size];
		for ( int i = 0; i < size; i++ )
		{
			temp[i] = cur.getData();
			cur = cur.getNext();
		}
		return temp;
	}
	/**
	 * @return a String containing the data from the linked list
	 */
	public String toString()
	{
		Object [] temp = toArray();
		String s = "[";
		for ( int i = 0; i < size; i++ )
		{
			s += temp[i];
			if ( i != size - 1 )
			{
				s += ", ";
			}
		}
		s += "]";
		return s;
	}
	public boolean enqueue( Object o )
	{
		return add( o );
	}
	public Object dequeue()
	{
		return remove( 0 );
	}
	public Object peek()
	{
		return get( 0 );
	}
	/**
	 * @author #DanTheMan
	 * @version 3.15
	 */
	private class Node
	{
		/** nope */
		private Object data;
		/** nope */
		private Node next;
		/** nope */
		public Node( Object data, Node next)
		{
			this.data = data;
			this.next = next;
		}
		/** nope */
		public Node( Object data )
		{
			this.data = data;
			this.next = null;
		}
		/** nope */
		public Object getData()
		{
			return data;
		}
		/** nope */
		public void setData( Object data )
		{
			this.data = data;
		}
		/** nope */
		public Node getNext()
		{
			return next;
		}
		/** nope */
		public void setNext( Node next )
		{
			this.next = next;
		}
	}
}

