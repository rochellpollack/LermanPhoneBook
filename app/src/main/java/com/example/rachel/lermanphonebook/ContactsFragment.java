package com.example.rachel.lermanphonebook;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
/*In this fragment we are trying to populate a list view with a prepopulated database. We are trying to do this by extending the
 sqliteAssetHelper class but ran into problems(we think the database isn't opening properly and we are therefore getting null pointer exceptions )
 We filled the list view with fake data so we can continue working with the DetailActivity and methods.*/

public class ContactsFragment extends android.support.v4.app.Fragment {

    static Context context;

    //name of tables in database
    static final String TABLE_Name = "contacts";

    //names of columns in table
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";

    //Lerman contacts Database
    private LermanContactsDatabase lcd;

    private Cursor contacts;

    // fake data arrays of info
    String[] firstNames = {"Rochel Leah", "Leah", "Shifra", "Gerry", "Shmuel"};
    String[] lastNames = {"Pollack", "Lerman", "Pollack", "Lerman", "Cohen"};
    String[] homePhoneNumber = {"5742322026", "5742347056", "5742322026", "5742877147", "7189675928"};
    String[] cellPhoneNumbers = {"5743436316", "5748505825", "5743436839", "5748761242", "9177481581"};
    String[] street = {"1220 Woodside", "1302 Woodside", "1220 Woodside", "1121 Woodside", "22 Igros Court"};
    String[] city = {"South Bend, IN", "South Bend, IN", "South Bend, IN", "South Bend, IN", "Staten Island, New York"};
    String[] zip = {"46614", "46614", "46614", "46614", "10309"};
    String[] email = {"rochellpollack@gmail.com", "lermanleah@gmail.com", "shifrapollack@gmail.com",
            "gerryl@steelwarehouse.net", "shmuelc@steelwarehouse.net"};

    //ListView to populate with First name and last name of each contacts
    private ListView lvContacts;
    ArrayAdapter<String> contactsAdapter;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        lvContacts = (ListView) rootView.findViewById(R.id.lv_contacts);

        //instantiating the Lerman database
        lcd = new LermanContactsDatabase(getActivity());

        contacts = lcd.getContacts();

        //converting the data from the cursor object to the listview
        ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.contact,
                contacts,
                new String[] {"FirstName", "LastName"},
                new int[] {R.id.list_item_contacts});

        //set the adapter
        lvContacts.setAdapter(adapter);


        /* We have tried creating a content provider which we saw will be beneficial, however we tried this before we knew what it was talking about
         but we are going to continue to work on it.
          */
//        ContentResolver cr = getActivity().getContentResolver();
//        Cursor cursor = cr.query(ContactsProvider.CONTENT_URI, null, null, null, null);



//        if (cursor != null && cursor.getCount() > 0){
//            cursor.moveToFirst();
//            do {
//                String firstName = cursor.getString(cursor.getColumnIndex(ContactsProvider.FIRST_NAME));
//                String lastName = cursor.getString(cursor.getColumnIndex(ContactsProvider.LAST_NAME));
//                ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
//                        R.layout.contact,
//                        cursor,
//                        new String[] {"FirstName", " LastName"},
//                        new int[] {R.id.list_item_contacts});
//
//                lvContacts.setAdapter(adapter);
//
//            }while (cursor.moveToNext());
//            cursor.close();
//        }







//        ArrayList<String> fakeContacts = new ArrayList<>();
//        fakeContacts.add("Rochel Leah Pollack");
//        fakeContacts.add("Leah Lerman");
//        fakeContacts.add("Shifra Pollack");
//        fakeContacts.add("Gerry Lerman");
//        fakeContacts.add("Shmuel Cohen");
//
//
//        contactsAdapter = new ArrayAdapter(getActivity(), R.layout.contact, R.id.list_item_contacts, fakeContacts);
//        lvContacts.setAdapter(contactsAdapter);

        //onClickListener for the listview
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = contactsAdapter.getItem(position).toString();
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, name);
                intent.putExtra("first_name", firstNames[position]);
                intent.putExtra("last_name", lastNames[position]);
                intent.putExtra("home_phone", homePhoneNumber[position]);
                intent.putExtra("cell_phone", cellPhoneNumbers[position]);
                intent.putExtra("street", street[position]);
                intent.putExtra("city", city[position]);
                intent.putExtra("zip", zip[position]);
                intent.putExtra("email", email[position]);
                startActivity(intent);
            }
        });

        return rootView;
    }

    /*
        when application is destroyed the database and cursor object are closed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        contacts.close();
        lcd.close();
    }

}

