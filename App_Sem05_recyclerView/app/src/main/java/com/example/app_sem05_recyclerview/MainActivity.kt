package com.example.app_sem05_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var contacts = ArrayList<Contact>()
    var contactAdapter = ContactAdapter(contacts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContacts()
        initView()
    }

    private fun initView() {
        val rvContact = findViewById<RecyclerView>(R.id.rvContact)

        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts.add(Contact("Jorge", "123456789"))
        contacts.add(Contact("Joselo", "123987789"))
        contacts.add(Contact("Joel", "321456789"))
        contacts.add(Contact("Jorge", "123456789"))
        contacts.add(Contact("Joselo", "123987789"))
        contacts.add(Contact("Joel1", "321456789"))
        contacts.add(Contact("Jorge1", "123456789"))
        contacts.add(Contact("Joselo1", "123987789"))
        contacts.add(Contact("Joel2", "321456789"))
        contacts.add(Contact("Jorge2", "123456789"))
        contacts.add(Contact("Joselo2", "123987789"))
        contacts.add(Contact("Joel3", "321456789"))
    }
}