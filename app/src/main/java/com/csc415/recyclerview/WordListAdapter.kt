package com.csc415.recyclerview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import java.util.*
import android.view.LayoutInflater

class WordListAdapter(context: Context?, wordList: LinkedList<String>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>()
{
	private var mWordList: LinkedList<String> = wordList
	private var mInflater: LayoutInflater = LayoutInflater.from(context)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder
	{
		val mItemView: View = mInflater.inflate(R.layout.wordlist_item, parent, false)
		return WordViewHolder(mItemView, this)
	}

	override fun onBindViewHolder(holder: WordViewHolder, position: Int)
	{
		val mCurrent = mWordList[position]
		holder.wordItemView.text = mCurrent
	}

	override fun getItemCount(): Int
	{
		return mWordList.size
	}

	inner class WordViewHolder(itemView: View, adapter: WordListAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener
	{
		val wordItemView: TextView = itemView.findViewById(R.id.word)
		private val mAdapter: WordListAdapter = adapter

		init
		{
			itemView.setOnClickListener(this);
		}

		override fun onClick(p0: View?)
		{
			val mPosition = layoutPosition
			val element: String = mWordList[mPosition]
			mWordList[mPosition] = "Clicked! $element"
			mAdapter.notifyDataSetChanged()
		}
	}
}