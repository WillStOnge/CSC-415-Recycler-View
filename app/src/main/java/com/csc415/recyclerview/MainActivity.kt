package com.csc415.recyclerview

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.csc415.recyclerview.databinding.ActivityMainBinding
import java.util.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity()
{
	private val mWordList: LinkedList<String> = LinkedList()
	private lateinit var binding: ActivityMainBinding
	private lateinit var mRecyclerView: RecyclerView
	private lateinit var mAdapter: WordListAdapter

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.toolbar)

		binding.fab.setOnClickListener {
			val wordListSize = mWordList.size
			mWordList.addLast("+ Word $wordListSize")
			mRecyclerView.adapter!!.notifyItemInserted(wordListSize)
			mRecyclerView.smoothScrollToPosition(wordListSize)
		}

		// Put initial data into the word list.
		for (i in 0..19)
			mWordList.addLast("Word $i")

		mRecyclerView = findViewById(R.id.recyclerview)
		mAdapter = WordListAdapter(this, mWordList)
		mRecyclerView.adapter = mAdapter
		mRecyclerView.layoutManager = LinearLayoutManager(this)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId)
		{
			// Reset recycler view
			R.id.action_settings -> {
				mWordList.clear()

				for (i in 0..19)
					mWordList.addLast("Word $i")

				mRecyclerView = findViewById(R.id.recyclerview)
				mAdapter = WordListAdapter(this, mWordList)
				mRecyclerView.adapter = mAdapter
				mRecyclerView.layoutManager = LinearLayoutManager(this)
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}
}